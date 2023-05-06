package hocheoltech.boos.oauth.info;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoogleUserInfo extends UserInfo{
    private String name;
    private String id;
    private String email;
    private String picture;
}
