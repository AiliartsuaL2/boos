package hocheoltech.boos.oauth.info;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "oauth")
@Getter @Setter
public class OAuthProperties {
    private Map<SocialType, Social> social;
    @Getter
    @Setter
    public static class Social {
        private String clientId;
        private String clientSecret;
    }
}