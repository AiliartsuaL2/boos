package hocheoltech.boos.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Builder // 빌더를 생성자 레벨에 두면, NPE 발생,, 클래스 레벨에 두려면 NoAgs,AllArgs를 안쓰던가, 둘다 쓰던가 해야함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
public class MembersBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBERS_SEQ")
    private Members members;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_SEQ")
    private Board board;

    @Builder // 빌더 생성자,,
    public MembersBoard(Members members, Board board){
        this.members = members;
        this.board = board;
        this.members.getMembersBoards().add(this);
        this.board.getMembersBoards().add(this);
    }

}
