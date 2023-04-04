package hocheoltech.boos.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BusinessCategory {
    @Id @GeneratedValue
    @Column(name="BUSINESS_CATEGORY")
    private Long seq;

    @Column(length = 10)
    private String categoryName;
}
