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

    // 카테고리 명
    private String categoryName;

    // 부모 카테고리 순번
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    @Builder
    public Category(Category parent){
        this.parent = parent;
        this.parent.children.add(this);
    }

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();
    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();

}
