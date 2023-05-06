package hocheoltech.boos.oauth.service;

import hocheoltech.boos.jwt.Token;
import hocheoltech.boos.oauth.info.UserInfo;
import hocheoltech.boos.oauth.token.OAuthToken;

public interface OAuthService {
    Token login(String accessToken);
    OAuthToken getAccessToken(String code);
    UserInfo getUserInfo(String accessToken);
}
