package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Message;
import hocheoltech.boos.dto.message.MessageDto;
import hocheoltech.boos.repository.Custom.MessageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> , MessageRepositoryCustom {
}
