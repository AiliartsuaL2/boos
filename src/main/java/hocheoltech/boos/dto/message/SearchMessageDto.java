package hocheoltech.boos.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchMessageDto {

    private String receiptNickname;
    private String senderNickname;
    private String senderId;
    private String receiptId;
    private String content;
}
