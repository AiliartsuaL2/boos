package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.UpdateBoardDto;
import hocheoltech.boos.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Commit
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void createBoard() {
        //given

        Long memberSeq = 3L;
        Long categorySeq = 1L;

        Optional<Category> categoryOptional = categoryRepository.findById(categorySeq);
        Category category = categoryOptional.get();

            Board board = Board.builder()
                    .title("제목")
                    .content("입니다")
                    .category(category)
                    .build();
            boardService.createBoard(board,memberSeq);
    }

    @Test
    void deleteBoard() {
        //given
        long boardSeq = 104L;
        long membersSeq = 3L;

        //when
        boardService.deleteBoard(membersSeq,boardSeq);

        //then
        Board boardDetail = boardService.getBoardDetail(boardSeq);

        Assert.isNull(boardDetail);

    }

    @Test
    void getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        for (Board board : boardList) {
            System.out.println("board = " + board.getContent());
        }
    }

    @Test
    void getBoardDetail(){
        //given
        long seq = 2;
        //when
        Board boardDetail = boardService.getBoardDetail(seq);
        String categoryName = boardDetail.getCategory().getCategoryName(); // DTO로 변환하던지 아니면 아래처럼 join해서 가져오던지 처리를 해야함.
        //then
        assertThat(categoryName).isEqualTo("비밀 게시판");
    }


    @Test
    @Transactional
    void updateBoard() {
        //given
        long boardSeq = 5L;

        Optional<Category> categoryOptional = categoryRepository.findById(2L);
        Category category = categoryOptional.get();

        Members noneOwner = memberService.findMember(3L);
        Members boardOwner = memberService.findMember(1L);

        Board board = Board.builder()
                .title("수정된 데이터!!!")
                .content("랍니다!!!")
                .category(category)
                .build();
        UpdateBoardDto updateBoardDto = new UpdateBoardDto(board, boardOwner.getSeq());


        //when
//        boardService.updateBoard(noneOwner.getSeq(),boardSeq,updateBoardDto); // 게시판 주인이 아님,
        boardService.updateBoard(boardOwner.getSeq(),boardSeq,updateBoardDto); // 게시판 주인
        //then
//        assertThat(boardDetail.getContent()).isEqualTo("랍니다");
    }
}