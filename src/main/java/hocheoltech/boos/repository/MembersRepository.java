package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {
    public boolean existById(String id);
}
