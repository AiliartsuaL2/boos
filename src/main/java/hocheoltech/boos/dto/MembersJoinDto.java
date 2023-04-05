package hocheoltech.boos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hocheoltech.boos.domain.BusinessCategory;
import hocheoltech.boos.domain.Terms;
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
    @JsonIgnore
    private List<Terms> termsList = new ArrayList<>();
}
