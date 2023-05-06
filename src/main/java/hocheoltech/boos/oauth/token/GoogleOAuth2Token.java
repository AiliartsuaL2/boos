package hocheoltech.boos.oauth.token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GoogleOAuth2Token extends OAuth2Token{
    private String accessToken;
    private int expiresIn;
    private String tokenType;
    private String scope;
    private String refreshToken;
}
