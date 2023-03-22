package hocheoltech.boos.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.QBoard;
import hocheoltech.boos.repository.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static hocheoltech.boos.domain.QBoard.board;
import static hocheoltech.boos.domain.QCategory.category;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public Board findBoardWithCategory(Long boardSeq){
        Board board = queryFactory.selectFrom(QBoard.board)
                .leftJoin(QBoard.board.category, category).fetchJoin()
                .where(QBoard.board.seq.eq(boardSeq))
                .fetchOne();
        return board;
    }
}
