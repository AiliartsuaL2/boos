package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class BlackList {

    // 순번
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // 차단한 ID
    @ManyToOne
    @JoinColumn(name = "MEMBERS_ID")
    private Members blockId;

    // 차단당한 ID
    @ManyToOne
    @JoinColumn(name = "MEMBERS_ID")
    private Members blockedId;

    // 차단 일시
    private LocalDateTime blockedTime;

}
