package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {
    // 순번
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // 부모 카테고리 순번
    private Long parent_category_seq; // null 허용

    // 카테고리 명
    private String category_name;
}
