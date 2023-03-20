package hocheoltech.boos.repository;

import hocheoltech.boos.domain.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
}
