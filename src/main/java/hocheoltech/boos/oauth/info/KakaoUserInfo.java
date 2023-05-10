package hocheoltech.boos.oauth.info;

import hocheoltech.boos.oauth.dto.KakaoAccountDto;
import hocheoltech.boos.oauth.dto.KakaoPropertiesDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KakaoUserInfo extends UserInfo{
    private long id;
    private String connectedAt;
    private KakaoPropertiesDto properties;
    private KakaoAccountDto kakaoAccount;

}
