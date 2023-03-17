package hocheoltech.boos.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Category {
    // 순번
    private long seq;

    // 부모 카테고리 순번
    private Long parent_category_seq; // null 허용

    // 카테고리 명
    private String category_name;
}
