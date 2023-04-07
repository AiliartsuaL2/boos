package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Blacklist;
import hocheoltech.boos.repository.Custom.BlacklistRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> , BlacklistRepositoryCustom {
}
