package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String senderId;

    // 수신자 id
    private String recipientId;

    // 발신일시
    private String sendTime;
}
