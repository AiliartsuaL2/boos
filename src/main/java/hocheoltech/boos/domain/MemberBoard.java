package hocheoltech.boos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MEMBERS_ID")
    private Members members;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="BOARD_ID")
    private Board board;

}
