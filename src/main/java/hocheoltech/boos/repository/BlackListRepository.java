package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<Blacklist, Long> {
}
