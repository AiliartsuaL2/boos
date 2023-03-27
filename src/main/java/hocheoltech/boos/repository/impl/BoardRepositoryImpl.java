package hocheoltech.boos.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.QBoard;
import hocheoltech.boos.domain.QMembers;
import hocheoltech.boos.domain.QMembersBoard;
import hocheoltech.boos.repository.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hocheoltech.boos.domain.QBoard.board;
import static hocheoltech.boos.domain.QCategory.category;
import static hocheoltech.boos.domain.QMembersBoard.membersBoard;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Board findBoardWithCategory(Long boardSeq){
        Board resultBoard = queryFactory.selectFrom(board)
                .join(board.category, category).fetchJoin()
                .where(board.seq.eq(boardSeq))
                .fetchOne();
        return resultBoard;
    }

    @Override
    public Page<Board> findBoardListByMembersSeq(Long membersSeq, Long categorySeq, Pageable pageable) {
        List<Board> boardList = queryFactory.selectFrom(board)
                .join(board.membersBoards, membersBoard).fetchJoin()
                .join(board.category, category).fetchJoin()
                .where(membersSeqEq(membersSeq)
                        .and(categorySeqEq(categorySeq)))
                .orderBy(membersBoard.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        JPAQuery<Board> countQuery = queryFactory.selectFrom(board)
                .join(board.membersBoards, membersBoard).fetchJoin()
                .join(board.category, category).fetchJoin()
                .where(membersSeqEq(membersSeq)
                        .and(categorySeqEq(categorySeq)));

        return PageableExecutionUtils.getPage(boardList,pageable,() -> countQuery.fetchCount());

    }


    private BooleanExpression categorySeqEq(Long categorySeq) {
        return categorySeq != null ? board.category.seq.eq(categorySeq) : null;
    }

    private BooleanExpression membersSeqEq(Long membersSeq) {
        return membersSeq != null ? membersBoard.members.seq.eq(membersSeq) : null;
    }
}
