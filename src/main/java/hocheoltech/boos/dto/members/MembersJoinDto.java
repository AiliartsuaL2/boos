package hocheoltech.boos.dto.members;

import hocheoltech.boos.dto.terms.LoginTermsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
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
    private List<LoginTermsDto> termsList ;

    @Builder
    public MembersJoinDto(String id, String name, String nickname, String password, String businessRegNum, String businessCategory, LocalDate openTime){
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.businessRegNum = businessRegNum;
        this.businessCategory = businessCategory;
        this.openTime = openTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        termsList = new ArrayList<>();
    }
}
