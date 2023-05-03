package hocheoltech.boos.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KakaoAcountDto {
    private String nickname;
    private String profileImage;
    private String thumbnailImage;
    private String email;
}
