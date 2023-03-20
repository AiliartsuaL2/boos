package hocheoltech.boos.repository;

import hocheoltech.boos.domain.MemberBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBoardRepository extends JpaRepository<MemberBoard, Long> {
}
