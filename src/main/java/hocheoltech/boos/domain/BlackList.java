package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BlackList {

    // 순번
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // 차단한 ID
    @ManyToOne()
    private String blockId;

    @ManyToOne
    // 차단당한 ID
    private String blockedId;

    // 차단 일시
    private String blockedTime;

}
