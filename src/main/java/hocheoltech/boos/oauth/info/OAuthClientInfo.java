package hocheoltech.boos.oauth.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// java bean 등록하며 해당 bean 사용시(OAuthInfo enum 에서) 아래의 setter 메서드와 @Value를 이용하여 properties의 값으로 상수 설정
@Component
public class OAuthClientInfo {
    public static String kakaoClientId ;
    public static String googleClientId ;
    public static String githubClientId ;
    public static String kakaoClientSecret ;
    public static String googleClientSecret ;
    public static String githubClientSecret ;

    @Value("${oauth.social.kakao.client-id}")
    public void setKakaoClientId(String kakaoClientId){
        this.kakaoClientId = kakaoClientId;
    }
    @Value("${oauth.social.google.client-id}")
    public void setGoogleClientId(String googleClientId){
        this.googleClientId = googleClientId;
    }
    @Value("${oauth.social.github.client-id}")
    public void setGithubClientId(String githubClientId){
        this.githubClientId = githubClientId;
    }
    @Value("${oauth.social.kakao.client-secret}")
    public void setKakaoClientSecret(String kakaoClientSecret){
        this.kakaoClientSecret = kakaoClientSecret;
    }
    @Value("${oauth.social.google.client-secret}")
    public void setGoogleClientSecret(String googleClientSecret){
        this.googleClientSecret = googleClientSecret;
    }
    @Value("${oauth.social.github.client-secret}")
    public void setGithubClientSecret(String githubClientSecret){
        this.githubClientSecret = githubClientSecret;
    }
}
