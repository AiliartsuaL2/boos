package hocheoltech.boos.repository.Custom;

import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.dto.message.SearchMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepositoryCustom {
    Page<MessageDto> findMessageList(SearchMessageDto SearchMessageDto, Pageable pageable);

}
