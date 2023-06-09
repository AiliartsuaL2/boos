package hocheoltech.boos.oauth.info;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GithubUserInfo extends UserInfo{
    private String nickname;
    private String id;
    private String email;
    private String picture;
}
