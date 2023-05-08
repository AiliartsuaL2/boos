package hocheoltech.boos.oauth.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.jwt.handler.JwtTokenProvider;
import hocheoltech.boos.oauth.info.GithubUserInfo;
import hocheoltech.boos.oauth.info.OAuthInfo;
import hocheoltech.boos.oauth.info.OAuthProperties;
import hocheoltech.boos.oauth.info.SocialType;
import hocheoltech.boos.oauth.token.GithubOAuthToken;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubOAuthService implements OAuthService{
    private final MembersRepository membersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthProperties oauthProperties;


    @Override
    public Token login(String accessToken) {
        GithubUserInfo githubUserInfo = getUserInfo(accessToken);
        String email = Optional.ofNullable(githubUserInfo.getEmail()).orElse("none");
        Members members = membersRepository.findMembersByEmail(email).orElse(null);
        // 신규회원인경우 회원 새로 생성
        if(members == null){
            Members newMembers = Members.builder()
                    .id(UUID.randomUUID().toString().replace("-", ""))
                    .email(email)
                    .nickname(githubUserInfo.getNickname())
                    .build();
            members = membersRepository.save(newMembers);
        }
        return jwtTokenProvider.createTokenWithRefresh(members.getEmail(), members.getRoles());
    }

    @Override
    public GithubOAuthToken getAccessToken(String code) {
        // POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
        // 이 때 필요한 라이브러리가 RestTemplate, 얘를 쓰면 http 요청을 편하게 할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //Github 데이터의 경우 json 방식이 invalid json
        // post 헤더에 json 옵션 넣어주기
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        HttpEntity request = new HttpEntity(headers);

        String clientId = oauthProperties.getSocial().get(SocialType.GITHUB).getClientId();
        System.out.println("clientId = " + clientId);


        // Uri 빌더 사용
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(OAuthInfo.GITHUB_INFO.getTokenUrl())
                .queryParam("client_id", OAuthInfo.GITHUB_INFO.getClientId())
                .queryParam("redirect_uri", OAuthInfo.GITHUB_INFO.getRedirectUri())
                .queryParam("client_secret", OAuthInfo.GITHUB_INFO.getClientSecret())
                .queryParam("code", code);

        ResponseEntity<String> response = restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.POST,
                request,
                String.class
        );

        // UnderScoreCase To Camel GsonBuilder,, githubOAuthToken 객체에 매핑
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        GithubOAuthToken githubOAuthToken = gson.fromJson(response.getBody(), GithubOAuthToken.class);
        log.info("깃허브 액세스 토큰 : " + githubOAuthToken.getAccessToken());

        return githubOAuthToken;
    }

    @Override
    public GithubUserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization","Bearer "+accessToken);

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                OAuthInfo.GITHUB_INFO.getInfoUrl(),
                HttpMethod.GET,
                request, // 요청시 보낼 데이터
                String.class // 요청시 반환 데이터 타입
        );
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        GithubUserInfo githubUserInfo = gson.fromJson(response.getBody(), GithubUserInfo.class);

        return githubUserInfo;
    }
}
