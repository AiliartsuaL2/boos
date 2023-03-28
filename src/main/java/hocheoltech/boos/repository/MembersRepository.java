package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, Long>, MembersRepositoryCustom {
    boolean existsById(String id);
    boolean existsByIdAndPassword(String id, String password);
    boolean existsByBusinessRegNum(String businessRegNum);
    Optional<Members> findById(String id);

}
