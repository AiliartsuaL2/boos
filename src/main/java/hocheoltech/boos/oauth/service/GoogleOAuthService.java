package hocheoltech.boos.oauth.service;

import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.oauth.info.GoogleUserInfo;
import hocheoltech.boos.oauth.info.UserInfo;
import hocheoltech.boos.oauth.token.GoogleOAuth2Token;
import hocheoltech.boos.oauth.token.OAuth2Token;

public class GoogleOAuthService implements OAuthService {
    @Override
    public Token login(String accessToken) {
        return null;
    }

    @Override
    public GoogleOAuth2Token getAccessToken(String code) {
        return null;
    }

    @Override
    public GoogleUserInfo getUserInfo(String accessToken) {
        return null;
    }
}
