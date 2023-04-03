package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Blacklist {

    // 순번
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLACKLIST_SEQ")
    private long seq;

    // 차단한 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "blocker_seq" , insertable = false, updatable = false)
    private Members blockId;

    // 차단당한 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "blocked_seq" , insertable = false, updatable = false)
    private Members blockedId;

    // 차단 일시
    private LocalDateTime blockedTime;

    @Builder
    public Blacklist(Members blockId, Members blockedId){
        this.blockId = blockId;
        this.blockedId = blockedId;
        this.blockedTime = LocalDateTime.now();
        this.blockId.getBlockList().add(this);
        this.blockedId.getBlockedList().add(this);
    }


}
