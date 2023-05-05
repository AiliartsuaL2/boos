package hocheoltech.boos.oauth.service;

import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.oauth.info.UserInfo;
import hocheoltech.boos.oauth.token.OAuth2Token;

public interface OAuthService {
    Token login(String accessToken);
    OAuth2Token getAccessToken(String code);
    UserInfo getUserInfo(String accessToken);
}
