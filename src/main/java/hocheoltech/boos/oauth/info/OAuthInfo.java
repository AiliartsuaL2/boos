package hocheoltech.boos.oauth.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OAuthInfo {
    KAKAO_INFO(
            "authorization_code",
            OAuthClientInfo.kakaoClientId,
            OAuthClientInfo.kakaoClientSecret,
            "http://localhost:8080/oauth/kakao/login",
            "https://kauth.kakao.com/oauth/token",
            "https://kapi.kakao.com/v2/user/me"
    ),
    GOOGLE_INFO(
            "authorization_code",
            OAuthClientInfo.googleClientId,
            OAuthClientInfo.googleClientSecret,
            "http://localhost:8080/oauth/google/login",
            "https://oauth2.googleapis.com/token",
            "https://www.googleapis.com/oauth2/v2/userinfo"
    ),
    GITHUB_INFO(
            "authorization_code",
            OAuthClientInfo.githubClientId,
            OAuthClientInfo.githubClientSecret,
            "http://localhost:8080/oauth/github/login",
            "https://github.com/login/oauth/access_token",
            "https://api.github.com/user"
    );

    private final String grantType ;
    private final String clientId ;
    private final String clientSecret ;
    private final String redirectUri ;
    private final String tokenUrl ;
    private final String infoUrl ;
}
