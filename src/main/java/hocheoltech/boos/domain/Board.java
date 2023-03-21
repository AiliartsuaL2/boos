package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="BOARD")
public class Board {


    // 순번
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_SEQ")
    private long seq;
    
    // 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_SEQ")
    private Category category;

    // 제목
    private String title;
    
    // 내용
    private String content;
    
    // 작성일시
    private LocalDateTime regTime;
    
    // 수정 여부
    private String modifyYn;
    
    // 수정 일시
    private LocalDateTime modifyTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBERS_SEQ") // 외래키가 있는쪽이 연관관계 주인
    private Members members;

    // 댓글
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL) //mappedBy 옵션 :  매핑된 컬럼의 변수명
    private List<Comment> comments = new ArrayList<>(); // 관례상 초기화해줌 (add시 NPE 방지)

    // 댓글추가
    public void addComment(Comment comment){
        comments.add(comment);
    }

    // 게시판 제목, 내용 , 카테고리 수정
    public void updateBoard(String title, String content, Category category){
        this.title = title;
        this.content = content;
        this.category = category;
        this.modifyYn = "Y";
        this.modifyTime = LocalDateTime.now();
    }
}
