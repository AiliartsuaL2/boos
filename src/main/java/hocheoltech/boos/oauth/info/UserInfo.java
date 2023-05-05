package hocheoltech.boos.oauth.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private long id;
    private String email;
    private String profileUrl;
}
