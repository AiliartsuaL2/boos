package hocheoltech.boos.dto.members;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMembersDto {
    private String id;
    private String password;
    private String nickname;
}
