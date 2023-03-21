package hocheoltech.boos.repository;

import hocheoltech.boos.domain.MembersBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersBoardRepository extends JpaRepository<MembersBoard, Long> {
}
