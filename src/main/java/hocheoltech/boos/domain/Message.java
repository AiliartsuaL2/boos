package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Message {

    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    // 발신자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "MEMBERS_SEQ" , insertable = false, updatable = false)
    private Members senderId;

    private String content;

    // 수신자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "MEMBERS_SEQ" , insertable = false, updatable = false)
    private Members recipientId;

    // 발신일시
    private LocalDateTime sendTime;

    @Builder
    public Message(Members sender, String content ,Members recipient){
        this.content = content;
        this.sendTime = LocalDateTime.now();
        this.senderId = sender;
        this.recipientId = recipient;
        this.senderId.getSendMessages().add(this);
        this.recipientId.getRecipientMessages().add(this);
    }
}
