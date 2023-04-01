package hocheoltech.boos.repository;

import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.dto.UpdateBoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MembersBoardRepository extends JpaRepository<MembersBoard, Long>{
}
