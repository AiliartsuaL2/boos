package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    // 내용
    private String content;
    // 작성일시
    private LocalDateTime regTime;
    //익명 여부
    private String anonymouseYn;


    @Builder
    public Comment(Board board, Members members) {
        this.board = board;
        this.board.getComments().add(this);
        this.members = members;
        this.members.getComments().add(this);
    }

    // 게시판
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_SEQ") // 외래키가 있는쪽이 연관관계 주인
    private Board board;
    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBERS_SEQ") // 외래키가 있는쪽이 연관관계 주인
    private Members members;


}
