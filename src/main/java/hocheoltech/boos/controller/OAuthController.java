package hocheoltech.boos.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.oauth.info.GoogleUserInfo;
import hocheoltech.boos.oauth.info.OAuthInfo;
import hocheoltech.boos.oauth.info.UserInfo;
import hocheoltech.boos.oauth.service.GoogleOAuthService;
import hocheoltech.boos.oauth.token.GoogleOAuth2Token;
import hocheoltech.boos.oauth.token.KakaoOAuth2Token;
import hocheoltech.boos.oauth.service.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final KakaoOAuthService kakaoOAuthService;
    private final GoogleOAuthService googleOAuthService;

    @GetMapping("/kakao/login")
    public String kakaoLogin(String code){
        KakaoOAuth2Token kakaoAccessToken = kakaoOAuthService.getAccessToken(code);
        Token token = kakaoOAuthService.login(kakaoAccessToken.getAccessToken());
        Gson gson = new Gson();
        return gson.toJson(token);
    }

    @GetMapping("/google/login")
    public String googleLogin(String code){
        GoogleOAuth2Token googleAccessToken = googleOAuthService.getAccessToken(code);
        Token token = googleOAuthService.login(googleAccessToken.getAccessToken());
        Gson gson = new Gson();
        return gson.toJson(token);
    }

}
