package hocheoltech.boos.repository;


import hocheoltech.boos.domain.Board;
import hocheoltech.boos.dto.BoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    Board findBoardWithCategory(Long boardSeq);

    Page<BoardListDto> findBoardListPaging(String nickname, String categoryName, String boardTitle, String boardContent , Pageable pageable);

}
