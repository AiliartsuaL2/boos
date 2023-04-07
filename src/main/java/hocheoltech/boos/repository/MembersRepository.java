package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.repository.Custom.MembersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, String>, MembersRepositoryCustom {
    Optional<Members> findMembersByIdAndPassword(String id, String password);
    boolean existsByBusinessRegNum(String businessRegNum);

}
