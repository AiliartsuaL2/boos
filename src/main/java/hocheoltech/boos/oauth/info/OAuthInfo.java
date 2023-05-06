package hocheoltech.boos.oauth.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OAuthInfo {
    KAKAO_INFO(
            "authorization_code",
            "373ab47c76e37b4418bb292d708ce2c0",
            "http://localhost:8080/oauth/kakao/login",
            "3boIieKSCSaBpPfVWI2aNv4Y5yAlIFHS",
            "https://kauth.kakao.com/oauth/token",
            "https://kapi.kakao.com/v2/user/me"
    ),
    GOOGLE_INFO(
            "authorization_code",
            "900804701090-sk6rt9ah5cp1tmg6ppudj48ki2hs29co.apps.googleusercontent.com",
            "http://localhost:8080/oauth/google/login",
            "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",
            "https://oauth2.googleapis.com/token",
            "https://www.googleapis.com/oauth2/v2/userinfo"
    ),
    GITHUB_INFO(
            null,
            null,
            null,
            null,
            null,
            null
    );

    private final String grantType ;
    private final String clientId ;
    private final String redirectUri ;
    private final String clientSecret ;
    private final String tokenUrl ;
    private final String infoUrl ;

}
