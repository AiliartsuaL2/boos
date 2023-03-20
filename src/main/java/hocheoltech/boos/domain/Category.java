package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {
    // 순번
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORY_ID")
    private long seq;

    // 부모 카테고리 순번
    private Long parent_category_seq; // null 허용

    // 카테고리 명
    private String category_name;

    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();
}
