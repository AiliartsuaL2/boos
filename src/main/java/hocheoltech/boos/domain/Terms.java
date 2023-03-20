package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    private String agreeTime;

    @ManyToOne
    @JoinColumn(name="MEMBERS_ID")
    private Members members;
}
