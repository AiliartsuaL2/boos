package hocheoltech.boos.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.dto.UpdateBoardDto;
import hocheoltech.boos.repository.MembersBoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static hocheoltech.boos.domain.QBoard.board;
import static hocheoltech.boos.domain.QMembers.members;
import static hocheoltech.boos.domain.QMembersBoard.membersBoard;

@RequiredArgsConstructor
@Repository
public class MembersBoardRepositoryImpl implements MembersBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public UpdateBoardDto findMembersBoard(UpdateBoardDto updateBoardDto) {
        List<Board> resultQuery = queryFactory.selectFrom(board)
                .leftJoin(board.membersBoards, membersBoard).fetchJoin()
                .leftJoin(membersBoard.members, members).fetchJoin()
                .where(members.seq.eq(updateBoardDto.getMembersSeq())
                .and (board.seq.eq(updateBoardDto.getBoardSeq())))
                .fetch();
        
        // 리팩토링 다시하기

        List<UpdateBoardDto> resultList = resultQuery.stream()
                .map(p -> UpdateBoardDto.builder()
                        .boardSeq(p.getSeq())
                        .boardTitle(p.getTitle())
                        .boardContent(p.getContent())
                        .boardCategory(p.getCategory())
                        .build()).collect(Collectors.toList());

        if(resultList.size() == 0){ // 일치하는 값이 없으면.. 사용자가 일치하지않음
            return null;
        }
        return resultList.get(0);
    }

}
