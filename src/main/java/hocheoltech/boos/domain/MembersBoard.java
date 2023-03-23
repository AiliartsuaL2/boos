package hocheoltech.boos.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @AllArgsConstructor
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
        this.members.getMembersBoards().add(this);
        this.board = board;
        this.board.getMembersBoards().add(this);
    }

}
