package hocheoltech.boos.dto.message;

import com.querydsl.core.annotations.QueryProjection;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private String messageSeq;
    private String senderId;
    private String receiptId;
    private String senderNickname;
    private String receiptNickname;
    private String content;
    private String sendTime;
    private String boxLocation;

    @Builder
    public MessageDto(String messageSeq, String senderId, String receiptId,String senderNickname,String receiptNickname, String content, String boxLocation){
        this.messageSeq = messageSeq;
        this.senderId = senderId;
        this.receiptId = receiptId;
        this.senderNickname = senderNickname;
        this.receiptNickname = receiptNickname;
        this.content = content;
        this.boxLocation = boxLocation;
    }


    @QueryProjection
    public MessageDto(long messageSeq, String senderId, String receiptId,String senderNickname,String receiptNickname, String content, LocalDateTime sendTime){
        this.messageSeq = String.valueOf(messageSeq);
        this.senderId = senderId;
        this.receiptId = receiptId;
        this.senderNickname = senderNickname;
        this.receiptNickname = receiptNickname;
        this.content = content;
        this.sendTime = sendTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
