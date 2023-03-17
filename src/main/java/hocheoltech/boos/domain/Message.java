package hocheoltech.boos.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {

    // 순번
    private long seq;

    // 발신자 id
    private String senderId;

    // 수신자 id
    private String recipientId;

    // 발신일시
    private String sendTime;
}
