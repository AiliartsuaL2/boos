package hocheoltech.boos.oauth.info;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter @Setter
@Component
public class Test {
    private static class commonInfo{
        public static String clientId;
        public static String clientSecret;
    }
    @Component
    @ConfigurationProperties(prefix = "oauth.kakao")
    @Getter
    public static class kakaoInfo extends commonInfo{

    }
    @Component
    @ConfigurationProperties(prefix = "oauth.google")
    @Getter
    public static class googleInfo extends commonInfo{

    }
    @Component
    @Getter @Setter
    public static class githubInfo extends commonInfo{
        @ConfigurationProperties(prefix = "oauth.github")
        public void setGithubInfo(String clientId, String clientSecret){
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }
    }
}
