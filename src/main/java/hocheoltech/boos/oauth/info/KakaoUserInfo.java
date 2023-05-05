package hocheoltech.boos.oauth.info;

import hocheoltech.boos.oauth.dto.KaKaoAccountDto;
import hocheoltech.boos.oauth.dto.KakaoPropertiesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class KakaoUserInfo extends UserInfo{
    private long id;
    private String connectedAt;
    private KakaoPropertiesDto properties;
    private KaKaoAccountDto kakaoAccount;

}
