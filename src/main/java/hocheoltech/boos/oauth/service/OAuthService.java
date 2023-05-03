package hocheoltech.boos.oauth.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hocheoltech.boos.oauth.dto.KakaoOAuth2Token;
import hocheoltech.boos.oauth.info.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService {
    public KakaoOAuth2Token getKakaoAccessToken(String code) {

        // POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
        // 이 때 필요한 라이브러리가 RestTemplate, 얘를 쓰면 http 요청을 편하게 할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);

        // Uri 빌더 사용
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(UserInfo.KAKAO_INFO.getTokenUrl())
                .queryParam("grant_type", UserInfo.KAKAO_INFO.getGrantType())
                .queryParam("client_id", UserInfo.KAKAO_INFO.getClientId())
                .queryParam("redirect_uri", UserInfo.KAKAO_INFO.getRedirectUri())
                .queryParam("client_secret", UserInfo.KAKAO_INFO.getClientSecret())
                .queryParam("code", code);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.POST,
                request,
                String.class
        );

        // UnderScoreCase To Camel GsonBuilder,, KakaoOAuth2Token 객체에 매핑
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        KakaoOAuth2Token kakaoOAuth2Token = gson.fromJson(responseEntity.getBody(), KakaoOAuth2Token.class);
        log.info("카카오 액세스 토큰 : " + kakaoOAuth2Token.getAccessToken());

        return kakaoOAuth2Token;
    }
    public String getUserKakaoInfo(KakaoOAuth2Token kakaoToken){
        // 이 때 필요한 라이브러리가 RestTemplate, 얘를 쓰면 http 요청을 편하게 할 수 있다.
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization","Bearer "+kakaoToken.getAccessToken());

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                UserInfo.KAKAO_INFO.getInfoUrl(),
                HttpMethod.POST,
                request, // 요청시 보낼 데이터
                String.class // 요청시 반환 데이터 타입
        );
        return response.getBody();
    }
}
