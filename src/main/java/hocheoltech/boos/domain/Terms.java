package hocheoltech.boos.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Terms {
    // 순번
    private long seq;

    // 회원 id
    private String membersId;

    // 약관 종류
    private String termsCategory;

    // 동의 여부
    private String agreeYn;

    // 동의 일시
    private String agreeTime;
}
