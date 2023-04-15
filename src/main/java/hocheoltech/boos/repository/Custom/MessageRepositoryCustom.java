package hocheoltech.boos.repository.Custom;

import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageRepositoryCustom {
    Optional<Message> findMessage(Long seq, String membersId, String boxLocation);
    Page<MessageDto> findSendedMessageList(SearchMessageDto SearchMessageDto, Pageable pageable);
    Page<MessageDto> findReceiptedMessageList(SearchMessageDto searchMessageDto, Pageable pageable);

}
