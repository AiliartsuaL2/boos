package hocheoltech.boos.domain;


import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.common.converter.TFCodeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms {
    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERMS_SEQ")
    private long seq;

    // 약관 종류
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TERMS_CATEGORY_SEQ")
    private TermsCategory termsCategory;

    // 동의 여부
    @Convert(converter = TFCodeConverter.class)
    @Column(columnDefinition = "char")
    private TFCode agreeYn;


    // 동의 일시
    private LocalDateTime agreeTime;

    // 약관 동의자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBERS_SEQ")
    private Members members;

    @Builder
    public Terms(TermsCategory termsCategory, String agreeYn, Members members){
        this.termsCategory = termsCategory;
        this.agreeYn = "Y".equals(agreeYn) ? TFCode.TRUE : TFCode.FALSE;
        this.agreeTime = LocalDateTime.now();
        this.members = members;
        this.members.getTerms().add(this);
    }
}
