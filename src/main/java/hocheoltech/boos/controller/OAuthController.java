package hocheoltech.boos.controller;

import hocheoltech.boos.jwt.Token;
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
    private final KakaoOAuthService oauthServiceKakao;

    @GetMapping("/kakao/login")
    public Token kakaoLogin(String code){
        KakaoOAuth2Token kakaoAccessToken = oauthServiceKakao.getKakaoAccessToken(code);
        Token token = oauthServiceKakao.login(kakaoAccessToken.getAccessToken());
        return token;
    }

    @GetMapping("/google/login")
    public String googleLogin(String accessToken){
//        KakaoOAuth2Token kakaoAccessToken = oauthService.getKakaoAccessToken(code);
//        Token token = oauthService.kakaoLogin(kakaoAccessToken.getAccessToken());
        return accessToken;
    }


}
