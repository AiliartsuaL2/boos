package hocheoltech.boos.service;

import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Comment;
import hocheoltech.boos.dto.comment.CommentDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

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
                .boardSeq(3L)
                .membersId("luvsole34")
                .anonymousYn(TFCode.FALSE)
                .content("댓글 테스트")
                .build();
        commentService.createComment(co);
        //when
//        Comment comment = commentRepository.findById(1L).orElseThrow(() -> new NoSuchElementException(ErrorMessage.NOT_EXIST_COMMENT.getMsg()));
//
//        //then
//        assertThat("juho123").isEqualTo(comment.getMembers().getId());

    }
    @Test
    void deleteComment(){
        //given
        CommentDto commentDto = CommentDto.builder()
                .seq(5L)
                .membersId("cy123")
                .anonymousYn(TFCode.FALSE)
                .build();

        //when
        commentService.deleteComment(commentDto);
        //then
        Comment comment1 = commentRepository.findById(5L).get();
        assertThat(comment1.getDeleteYn()).isEqualTo(TFCode.TRUE);



    }
}