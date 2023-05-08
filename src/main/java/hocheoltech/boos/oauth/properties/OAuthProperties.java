package hocheoltech.boos.oauth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "oauth")
@Getter @Setter
//TODO Setter 말고 Constructor로 처리하기
public class OAuthProperties {
    private Map<SocialType, Social> social;
    @Getter
    @Setter
    public static class Social {
        private String grantType = "authorization_code";
        private String clientId;
        private String clientSecret;
        private String redirectUri ;
        private String tokenUrl ;
        private String infoUrl ;
    }
}