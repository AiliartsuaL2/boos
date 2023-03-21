package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.repository.CategoryRepository;
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
        Optional<Category> categoryOptional = categoryRepository.findById(2L);
        Category category = categoryOptional.get();

        Board board = Board.builder()
                .title("새로운 데이터")
                .content("입니다")
                .regTime(LocalDateTime.now())
                .category(category)
                .modifyYn("Y")
                .build();

        Board createdBoard = boardService.createBoard(board,2L);
        createdBoard.toString();
    }

    @Test
    void deleteBoard() {
        //given
        long seq = 4;

        //when
        boardService.deleteBoard(seq);

        //then
        Board boardDetail = boardService.getBoardDetail(seq);

        Assert.isNull(boardDetail);

    }

    @Test
    void getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        for (Board board : boardList) {
            System.out.println("board = " + board.getContent());
        }
    }

    public static void main(String[] args) {

    }

    @Test
    void updateBoard() {
        //given
        long seq = 6L;

        Optional<Category> categoryOptional = categoryRepository.findById(2L);
        Category category = categoryOptional.get();

        Members member = memberService.findMember(3L);

        Board board = Board.builder()
                .title("수정된 데이터")
                .content("랍니다")
                .regTime(LocalDateTime.now())
                .category(category)
                .modifyYn("Y")
                .build();

        //when
        boardService.updateBoard(seq,board);
        Board boardDetail = boardService.getBoardDetail(seq);
        //then
        assertThat(boardDetail.getContent()).isEqualTo("랍니다");
    }
}