package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Message {

    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;


    // 발신자 id
    @ManyToOne
    @JoinColumn(name= "MEMBERS_SEQ" , insertable = false, updatable = false)
    private Members senderId;


    // 수신자 id
    @ManyToOne
    @JoinColumn(name= "MEMBERS_SEQ" , insertable = false, updatable = false)
    private Members recipientId;

    // 발신일시
    private LocalDateTime sendTime;
}
