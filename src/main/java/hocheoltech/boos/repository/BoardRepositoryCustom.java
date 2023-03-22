package hocheoltech.boos.repository;


import hocheoltech.boos.domain.Board;

public interface BoardRepositoryCustom {

    Board findBoardWithCategory(Long boardSeq);

}
