package hocheoltech.boos.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hocheoltech.boos.dto.comment.CommentDto;
import hocheoltech.boos.dto.comment.QCommentDto;
import hocheoltech.boos.repository.Custom.CommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hocheoltech.boos.domain.QComment.comment;

@RequiredArgsConstructor
@Repository
@Slf4j
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentDto> findCommentListByBoardSeq(long boardSeq) {
        List<CommentDto> result = queryFactory.select(new QCommentDto(
                        comment.seq,
                        comment.board.seq,
                        comment.members.nickname,
                        comment.content,
                        comment.regTime,
                        comment.anonymousYn
                )).from(comment)
                .where(comment.board.seq.eq(boardSeq))
                .fetch();

        return result;
    }
}
