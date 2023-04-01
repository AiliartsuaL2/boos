package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Comment;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.CommentDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BoardRepository;
import hocheoltech.boos.repository.CommentRepository;
import hocheoltech.boos.repository.MembersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class CommentServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;
    @Test
    void createComment() {
        //given

        CommentDto co = CommentDto.builder()
                .boardSeq("249")
                .writerId("juho123")
                .anonymousYn("N")
                .content("댓글 테스트")
                .build();
        commentService.createComment(co);
        //when
        Comment comment = commentRepository.findById(1L).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_COMMENT.getMsg()));

        //then
        assertThat("juho123").isEqualTo(comment.getMembers().getId());

    }
}