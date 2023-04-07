package hocheoltech.boos.repository.Custom;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.dto.board.BoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepositoryCustom {

    Optional<Board> findBoardWithCategory(Long boardSeq);

    Page<BoardListDto> findBoardListPaging(BoardListDto boardListDto , Pageable pageable);

}
