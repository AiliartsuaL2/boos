package hocheoltech.boos.controller;

import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.oauth.dto.KakaoOAuth2Token;
import hocheoltech.boos.oauth.info.KakaoUserInfo;
import hocheoltech.boos.oauth.service.OAuthService;
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
    private final OAuthService oauthService;

    @GetMapping("/kakao/login")
    public Token login(String code){
        KakaoOAuth2Token kakaoAccessToken = oauthService.getKakaoAccessToken(code);
        Token token = oauthService.kakaoLogin(kakaoAccessToken.getAccessToken());
        return token;
    }

}
