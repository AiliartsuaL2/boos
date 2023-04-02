package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Comment;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.CommentDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BoardRepository;
import hocheoltech.boos.repository.CommentRepository;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final MembersRepository membersRepository;
    private final BoardRepository boardRepository;

    @Transactional// CUD 상세 레벨엔 readOnly false(default)
    public CommentDto createComment(CommentDto commentDto){
        Members members = membersRepository.findById(commentDto.getWriterId()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg())
        );
        Board board = boardRepository.findById(Long.parseLong(commentDto.getBoardSeq())).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BOARD.getMsg())
        );
        Comment comment = Comment.builder()
                .members(members)
                .board(board)
                .content(commentDto.getContent())
                .anonymousYN(commentDto.getAnonymousYn())
                .build();
        commentRepository.save(comment);
        return commentDto;
    }

    @Transactional
    public void deleteComment(CommentDto commentDto){
        Members members = membersRepository.findById(commentDto.getWriterId()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        int result = commentRepository.deleteBySeqAndMembersSeq(Long.parseLong(commentDto.getSeq()), members.getSeq());
        if(result == 0){
            throw new RejectedExecutionException(ErrorMessage.UNAUTHORIZED_PERMISSION.getMsg());
        }
    }

}
