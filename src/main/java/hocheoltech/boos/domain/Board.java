package hocheoltech.boos.domain;


import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.common.converter.TFCodeConverter;
import hocheoltech.boos.dto.board.UpdateBoardDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="BOARD")
@AllArgsConstructor
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
    @Column(length = 20)
    private String title;
    
    // 내용
    @Column(length = 1000)
    private String content;
    
    // 작성일시
    private LocalDateTime regTime;

    //작성자(닉네임),, 비정규화 진행 (members 테이블과 정규화 시키면 쿼리작업이 너무 커짐)
    @Column(length = 10)
    private String writer;

    // 수정 여부
    @Convert(converter = TFCodeConverter.class)
    @Column(columnDefinition = "char")
    private TFCode modifyYn;

    // 수정일시
    private LocalDateTime modifyTime;

    //삭제여부
    @Convert(converter = TFCodeConverter.class)
    @Column(columnDefinition = "char")
    private TFCode deleteYn;

    //삭제일시
    private LocalDateTime deleteTime;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<MembersBoard> membersBoards;

    // 댓글
    @OneToMany(mappedBy = "board") //mappedBy 옵션 :  매핑된 컬럼의 변수명
    private List<Comment> comments = new ArrayList<>(); // 관례상 초기화해줌 (add시 NPE 방지)


    @Builder
    public Board(String title, String content, Category category, String writer, MembersBoard membersBoard){
        this.title = title;
        this.content = content;
        this.regTime = LocalDateTime.now();
        this.category = category;
        this.writer = writer;
        this.category.getBoards().add(this);
        this.membersBoards = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.deleteYn = TFCode.FALSE;
        this.modifyYn = TFCode.FALSE;
        this.membersBoards.add(membersBoard);
    }
    // 게시판 제목, 내용 , 카테고리 수정
    public void updateBoard(UpdateBoardDto updateBoardDto){
        this.title = updateBoardDto.getBoardTitle();
        this.content = updateBoardDto.getBoardContent();
        this.category = updateBoardDto.getBoardCategory();
        this.modifyYn = TFCode.TRUE;
        this.modifyTime = LocalDateTime.now();
    }

    // comment의 deleteYn Column을 Y로 바꿔줌
    public void deleteBoard(){
        this.deleteYn = TFCode.TRUE;
        this.deleteTime = LocalDateTime.now();
        for (Comment comment : this.comments) {
            comment.deleteComment();
        }
    }

}
