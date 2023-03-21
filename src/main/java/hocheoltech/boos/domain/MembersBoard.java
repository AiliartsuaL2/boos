package hocheoltech.boos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembersBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seq")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBERS_SEQ")
    private Members members;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_SEQ")
    private Board board;

}
