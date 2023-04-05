package hocheoltech.boos.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MembersLoginDto {
    private String id;
    private String password;
}
