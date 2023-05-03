package hocheoltech.boos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hocheoltech.boos.oauth.dto.KakaoOAuth2Token;
import hocheoltech.boos.oauth.info.UserInfo;
import hocheoltech.boos.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final OAuthService oauthService;

    @GetMapping("/kakao/callback")
    public String test(String code){
        KakaoOAuth2Token kakaoAccessToken = oauthService.getKakaoAccessToken(code);
        String userKakaoInfo = oauthService.getUserKakaoInfo(kakaoAccessToken);
        return userKakaoInfo;
    }

}
