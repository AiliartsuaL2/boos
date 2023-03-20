package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms {
    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
