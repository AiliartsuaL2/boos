package hocheoltech.boos.domain;


import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.common.converter.TFCodeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Message {

    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_SEQ")
    private long seq;

    // 발신자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "SENDER_ID")
    private Members senderId;

    // 내용
    @Column(length = 100)
    private String content;

    // 수신자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "RECIPIENT_ID")
    private Members recipientId;

    @Convert(converter = TFCodeConverter.class)
    @Column(columnDefinition = "char")
    private TFCode deleteYn;
    //삭제일시
    private LocalDateTime deleteTime;

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
        this.deleteYn = TFCode.FALSE;
    }

    public void deleteMessage(){
        this.deleteYn = TFCode.TRUE;
        this.deleteTime = LocalDateTime.now();
    }

}
