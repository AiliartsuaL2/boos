package hocheoltech.boos.dto.members;

import hocheoltech.boos.dto.terms.LoginTermsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    private List<LoginTermsDto> termsList = new ArrayList<>();
}
