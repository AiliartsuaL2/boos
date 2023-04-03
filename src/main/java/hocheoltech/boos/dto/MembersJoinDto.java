package hocheoltech.boos.dto;

import hocheoltech.boos.domain.BusinessCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembersJoinDto {
    private String id;
    private String name;
    private String nickname;
    private String password;
    private String businessRegNum;
    private String businessCategory;
    private String openTime;
}
