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

    // 게시판
    @ManyToOne
    @JoinColumn(name="BOARD_ID") // 외래키가 있는쪽이 연관관계 주인
    private Board board;

    // 작성자
    @ManyToOne
    @JoinColumn(name="MEMBERS_ID") // 외래키가 있는쪽이 연관관계 주인
    private Members members;

}
