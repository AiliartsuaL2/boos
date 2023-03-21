package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {
    boolean existsById(String id);
    boolean existsByIdAndPassword(String id, String password);
}
