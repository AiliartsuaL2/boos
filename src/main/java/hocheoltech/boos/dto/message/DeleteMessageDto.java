package hocheoltech.boos.dto.message;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeleteMessageDto {
    List<Long> messageSeqList ;
    String boxLocation;
    String membersId;

    @Builder
    public DeleteMessageDto(List<Long> messageSeqList, String boxLocation, String membersId){
        this.messageSeqList = messageSeqList;
        this.boxLocation = boxLocation;
        this.membersId = membersId;
    }
}
