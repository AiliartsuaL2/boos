package hocheoltech.boos.dto.message;

import hocheoltech.boos.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private String messageSeq;
    private String senderId;
    private String receiptId;
    private String content;
    private String sendTime;

    public MessageDto(Message message){
        this.messageSeq = String.valueOf(message.getSeq());
        this.senderId = message.getSenderId().getId();
        this.receiptId = message.getRecipientId().getId();
        this.content = message.getContent();
        this.sendTime = message.getSendTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
