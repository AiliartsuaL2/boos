package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
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

    // 게시판 순번
    private long boardSeq;

    // 내용
    private String content;

    // 작성일시
    private String regTime;

    //익명 여부
    private String anonymouseYn;

    @ManyToOne
    @JoinColumn(name="BOARD_ID") // 외래키가 있는쪽이 연관관계 주인
    private Board board;

    @ManyToOne
    @JoinColumn(name="MEMBERS_ID") // 외래키가 있는쪽이 연관관계 주인
    private Members members;

}
