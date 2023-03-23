package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {
    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // 약관 종류
    private String termsCategory;

    // 동의 여부
    private String agreeYn;

    // 동의 일시
    private LocalDateTime agreeTime;

    // 약관 동의자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBERS_SEQ")
    private Members members;

    @Builder
    public Terms(Members members){
        this.members = members;
        this.members.getTerms().add(this);
    }
}
