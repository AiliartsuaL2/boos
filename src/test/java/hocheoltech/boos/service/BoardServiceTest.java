package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.MembersBoard;
import hocheoltech.boos.domain.Members;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Commit
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    @Transactional
    void createBoard() {
        //given
        LocalDate openDate = parse("2022-12-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Members members = Members.builder()
                .id("jpajpajpa")
                .name("이증오")
                .password("qwqwqw1212")
                .nickname("asdfsadf")
                .joinTime(LocalDateTime.now())
                .openTime(openDate)
                .businessCategory("사업")
                .businessRegNum("202303020")
                .build();

        Category category = Category.builder()
                .category_name("jpa 카테골이")
                .build();

        Board board = Board.builder()
                .title("jpa제목")
                .content("jpa내용")
                .regTime(LocalDateTime.now())
                .category(category)
                .modifyYn("N")
                .build();

        MembersBoard membersBoard = MembersBoard.builder()
                .members(members)
                .board(board)
                .build();
        //when

        boardService.createBoard(board);

        //then


    }

    @Test
    void deleteBoard() {
        //given
        long seq = 4;

        //when
        boardService.deleteBoard(seq);

        //then
        Optional<Board> boardDetail = boardService.getBoardDetail(seq);

        Assert.isNull(boardDetail);

    }

    @Test
    void getBoardList() {
    }

    @Test
    void getBoardDetail() {
        //given
        long seq = 5;
        //when
        Optional<Board> boardDetail = boardService.getBoardDetail(seq);
        //then
//        assertThat(boardDetail.getSeq()).isEqualTo(5);
    }
}