package hocheoltech.boos.controller;

import com.google.gson.Gson;
import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.oauth.service.GithubOAuthService;
import hocheoltech.boos.oauth.service.GoogleOAuthService;
import hocheoltech.boos.oauth.token.GithubOAuthToken;
import hocheoltech.boos.oauth.token.GoogleOAuthToken;
import hocheoltech.boos.oauth.token.KakaoOAuthToken;
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
    private final GithubOAuthService githubOAuthService;

    @GetMapping("/kakao/login")
    public String kakaoLogin(String code){
        KakaoOAuthToken kakaoOAuthToken = kakaoOAuthService.getAccessToken(code);
        Token token = kakaoOAuthService.login(kakaoOAuthToken.getAccessToken());
        Gson gson = new Gson();
        return gson.toJson(token);
    }

    @GetMapping("/google/login")
    public String googleLogin(String code){
        GoogleOAuthToken googleOAuthToken = googleOAuthService.getAccessToken(code);
        Token token = googleOAuthService.login(googleOAuthToken.getAccessToken());
        Gson gson = new Gson();
        return gson.toJson(token);
    }

    @GetMapping("/github/login")
    public String githubLogin(String code){
        GithubOAuthToken githubOAuthToken = githubOAuthService.getAccessToken(code);
        Token token = githubOAuthService.login(githubOAuthToken.getAccessToken());
        Gson gson = new Gson();
        return gson.toJson(token);
    }

}
