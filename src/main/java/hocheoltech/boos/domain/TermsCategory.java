package hocheoltech.boos.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TermsCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERMS_CATEGORY_SEQ")
    private Long seq;

    @Column(length = 10)
    private String title;

    @Column(length = 50)
    private String content;
}
