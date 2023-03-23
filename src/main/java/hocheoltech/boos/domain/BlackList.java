package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class BlackList {

    // 순번
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // 차단한 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "MEMBERS_SEQ" , insertable = false, updatable = false)
    private Members blockId;

    // 차단당한 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "MEMBERS_SEQ" , insertable = false, updatable = false)
    private Members blockedId;

    // 차단 일시
    private LocalDateTime blockedTime;

    @Builder
    public BlackList(Members blockId, Members blockedId){
        this.blockId = blockId;
        this.blockedId = blockedId;
        this.blockedTime = LocalDateTime.now();
    }


}
