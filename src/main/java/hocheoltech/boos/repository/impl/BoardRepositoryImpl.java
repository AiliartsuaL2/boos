package hocheoltech.boos.repository.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.*;
import hocheoltech.boos.dto.board.BoardDetailDto;
import hocheoltech.boos.dto.board.BoardListDto;
import hocheoltech.boos.dto.board.QBoardDetailDto;
import hocheoltech.boos.dto.board.QBoardListDto;
import hocheoltech.boos.repository.Custom.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static hocheoltech.boos.domain.QBoard.board;
import static hocheoltech.boos.domain.QCategory.category;
import static hocheoltech.boos.domain.QComment.comment;
import static hocheoltech.boos.domain.QMembersBoard.membersBoard;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BoardDetailDto> findBoardDetail(Long boardSeq){
        BoardDetailDto boardDetailDto = queryFactory.select(new QBoardDetailDto(
                        board.seq,
                        board.title,
                        board.content,
                        board.writer,
                        board.regTime,
                        board.category.categoryName,
                        board.modifyYn,
                        board.deleteYn,
                        board.modifyTime))
                .from(board)
                .join(board.category, category)
                .where(board.seq.eq(boardSeq))
                .fetchOne();
        return Optional.ofNullable(boardDetailDto);
    }

    @Override
    public PageImpl<BoardListDto> findBoardListPaging(BoardListDto boardListDto , Pageable pageable) {

        List<BoardListDto> resultList = queryFactory.select(new QBoardListDto(board.seq,
                        board.title,
                        board.content,
                        membersBoard.members.nickname,
                        board.category.categoryName,
                        board.regTime,
                        board.modifyYn,
                        board.modifyTime)).
                from(board)
                .join(board.membersBoards, membersBoard)
                .join(board.category, category)
                .where(membersNicknameContains(boardListDto.getWriter())
                        .and(categoryNameContains(boardListDto.getCategoryName()))
                        .and(boardTitleContains(boardListDto.getTitle()))
                        .and(boardContentContains(boardListDto.getContent()))
                        .and(board.deleteYn.eq(TFCode.FALSE)) // 삭제처리된 게시물은 표시안함
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(boardSort(pageable))
                .fetch();

        Long count = queryFactory.select(board.count())
                .from(board)
                .join(board.membersBoards, membersBoard)
                .join(board.category, category)
                .where(membersNicknameContains(boardListDto.getWriter())
                        .and(categoryNameContains(boardListDto.getCategoryName()))
                        .and(boardTitleContains(boardListDto.getTitle()))
                        .and(boardContentContains(boardListDto.getContent()))
                        .and(board.deleteYn.eq(TFCode.FALSE)) // 삭제처리된 게시물은 표시안함
                )
                .fetchOne();

        return new PageImpl<>(resultList, pageable, count);
    }


    private BooleanExpression categoryNameContains(String categoryName) {
        return categoryName != null ? board.category.categoryName.contains(categoryName) : null;
    }

    private BooleanExpression membersNicknameContains(String nickname) {
        return nickname != null ? membersBoard.members.nickname.contains(nickname) : null;
    }

    private BooleanExpression boardTitleContains(String title) {
        return title != null ? board.title.contains(title) : null;
    }

    private BooleanExpression boardContentContains(String boardContent) {
        return boardContent != null ? board.content.contains(boardContent) : null;
    }

    private OrderSpecifier<?> boardSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : pageable.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                switch (order.getProperty()){
                    case "boardSeq":
                        return new OrderSpecifier(direction, board.seq);
                    case "regTime":
                        return new OrderSpecifier(direction, board.regTime);
                }
            }
        }
        return null;
    }

}
