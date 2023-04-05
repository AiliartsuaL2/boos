package hocheoltech.boos.domain;


import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.common.converter.TFCodeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_SEQ")
    private long seq;
    // 내용
    @Column(length = 100)
    private String content;
    // 작성일시
    private LocalDateTime regTime;

    //익명 여부
    @Convert(converter = TFCodeConverter.class)
    @Column(columnDefinition = "char")
    private TFCode anonymousYn;

    //삭제 여부
    @Convert(converter = TFCodeConverter.class)
    @Column(columnDefinition = "char")
    private TFCode deleteYn;
    // 게시판
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_SEQ") // 외래키가 있는쪽이 연관관계 주인
    private Board board;
    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBERS_ID") // 외래키가 있는쪽이 연관관계 주인
    private Members members;

    @Builder
    public Comment(String content, String anonymousYN, Board board, Members members) {
        this.content = content;
        this.anonymousYn = "Y".equals(anonymousYN) ? TFCode.TRUE : TFCode.FALSE;
        this.regTime = LocalDateTime.now();
        this.board = board;
        this.board.getComments().add(this);
        this.members = members;
        this.members.getComments().add(this);
        this.deleteYn = TFCode.FALSE;
    }


}
