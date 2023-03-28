package hocheoltech.boos.repository;


import hocheoltech.boos.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    Board findBoardWithCategory(Long boardSeq);

    Page<Board> findBoardListPaging(Long membersSeq,Long categorySeq, String boardTitle, String boardContent ,  Pageable pageable);

}
