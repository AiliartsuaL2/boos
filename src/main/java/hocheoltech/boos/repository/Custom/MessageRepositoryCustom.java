package hocheoltech.boos.repository.Custom;

import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepositoryCustom {
    Page<MessageDto> findSendedMessageList(SearchMessageDto SearchMessageDto, Pageable pageable);
    Page<MessageDto> findReceiptedMessageList(SearchMessageDto searchMessageDto, Pageable pageable);

}
