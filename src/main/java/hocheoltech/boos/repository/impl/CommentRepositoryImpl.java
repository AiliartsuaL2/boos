package hocheoltech.boos.repository.impl;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.dto.comment.CommentDto;
import hocheoltech.boos.dto.comment.QCommentDto;
import hocheoltech.boos.repository.Custom.CommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hocheoltech.boos.domain.QBlacklist.blacklist;
import static hocheoltech.boos.domain.QComment.comment;
import static hocheoltech.boos.domain.QMembers.members;

@RequiredArgsConstructor
@Repository
@Slf4j
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentDto> findCommentListByBoardSeq(long boardSeq, String membersId) {
        List<CommentDto> result = queryFactory.select(new QCommentDto(
                        comment.seq,
                        comment.board.seq,
                        comment.members.nickname,
                        comment.content,
                        comment.regTime,
                        comment.anonymousYn
                )).from(comment)
                .join(comment.members, members)
                .where(comment.board.seq.eq(boardSeq)
                        .and(comment.seq.notIn(JPAExpressions
                                .select(comment.seq)
                                .from(comment)
                                .join(blacklist).on(comment.members.eq(blacklist.blockedId))
                                .where(blacklist.blockerId.id.eq(membersId)))))
                .fetch();

        return result;
    }
}
