package hocheoltech.boos.oauth.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.jwt.handler.JwtTokenProvider;
import hocheoltech.boos.oauth.info.GoogleUserInfo;
import hocheoltech.boos.oauth.properties.OAuthProperties;
import hocheoltech.boos.oauth.token.GoogleOAuthToken;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor

public class GoogleOAuthService implements OAuthService {
    private final MembersRepository membersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthProperties oAuthProperties;

    @Override
    public Token login(String accessToken) {
        GoogleUserInfo googleUserInfo = getUserInfo(accessToken);
        String email = Optional.ofNullable(googleUserInfo.getEmail()).orElse("none");
        Members members = membersRepository.findMembersByEmail(email).orElse(null);
        // 신규회원인경우 회원 새로 생성
        if(members == null){
            Members newMembers = Members.builder()
                    .id(UUID.randomUUID().toString().replace("-", ""))
                    .email(email)
                    .name(googleUserInfo.getName())
                    .build();
            members = membersRepository.save(newMembers);
        }
        return jwtTokenProvider.createTokenWithRefresh(members.getId(), members.getRoles());
    }

    @Override
    public GoogleOAuthToken getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);

        /*
         https://accounts.google.com/o/oauth2/v2/auth?scope=profile&response_type=code
         &client_id="할당받은 id"&redirect_uri="access token 처리")
         로 Redirect URL을 생성하는 로직을 구성
         */
        // Uri 빌더 사용
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(oAuthProperties.getGoogle().getTokenUrl())
                .queryParam("grant_type", oAuthProperties.getGoogle().getGrantType())
                .queryParam("client_id",  oAuthProperties.getGoogle().getClientId())
                .queryParam("client_secret",  oAuthProperties.getGoogle().getClientSecret())
                .queryParam("redirect_uri",  oAuthProperties.getGoogle().getRedirectUri())
                .queryParam("code", code);

        ResponseEntity<String> response = restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.POST,
                request,
                String.class
        );

        // UnderScoreCase To Camel GsonBuilder,, googleOAuth2Token 객체에 매핑
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        GoogleOAuthToken googleOAuthToken = gson.fromJson(response.getBody(), GoogleOAuthToken.class);
        log.info("구글 액세스 토큰 : " + googleOAuthToken.getAccessToken());

        return googleOAuthToken;
    }

    @Override
    public GoogleUserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization","Bearer "+accessToken);

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                oAuthProperties.getGoogle().getInfoUrl(),
                HttpMethod.GET,
                request, // 요청시 보낼 데이터
                String.class // 요청시 반환 데이터 타입
        );

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        GoogleUserInfo googleUserInfo = gson.fromJson(response.getBody(), GoogleUserInfo.class);

        return googleUserInfo;
    }
}
