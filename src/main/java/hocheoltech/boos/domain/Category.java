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
    @Column(name="CATEGORY_SEQ")
    private long seq;

    // 부모 카테고리 순번
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_SEQ")
    private Category parentCategorySeq; // null 허용


    @OneToMany(mappedBy = "parentCategorySeq")
    private List<Category> child = new ArrayList<>();

    // 카테고리 명
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();
}
