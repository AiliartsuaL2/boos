package hocheoltech.boos.repository;

import hocheoltech.boos.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> , BoardRepositoryCustom{
    Optional<Board> findBoardBySeqAndWriter(long boardSeq, String writer);
}
