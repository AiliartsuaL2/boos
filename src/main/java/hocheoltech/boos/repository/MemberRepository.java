package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, String> {
}
