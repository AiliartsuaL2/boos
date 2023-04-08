package hocheoltech.boos.repository.Custom;

import hocheoltech.boos.dto.comment.CommentDto;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentDto> findCommentListByBoardSeq(long boardSeq);
}
