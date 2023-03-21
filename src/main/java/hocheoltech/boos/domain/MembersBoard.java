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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MEMBERS_SEQ")
    private Members members;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="BOARD_ID")
    private Board board;

}
