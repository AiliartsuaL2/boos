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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

@SpringBootTest
@Commit
class MembersBoardServiceTest {
    @Autowired
    MemberBoardService memberBoardService;
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    CategoryService categoryService;

    @Test
    @Transactional
    void save(){

        //given
        LocalDate openDate = parse("2022-12-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Members newMember = Members.builder()
                .id("jpajpajpa")
                .name("이증오")
                .password("qwqwqw1212")
                .nickname("asdfsadf")
                .joinTime(LocalDateTime.now())
                .openTime(openDate)
                .businessCategory("사업")
                .businessRegNum("202303020")
                .build();

        Category newCategory = Category.builder()
                .categoryName("jpa 카테골이2")
                .build();

        Category category = categoryService.createCategory(newCategory);

        Board newBoard = Board.builder()
                .title("jpa제목")
                .content("jpa내용")
                .regTime(LocalDateTime.now())
                .category(category)
                .modifyYn("N")
                .build();

        Board board = boardService.createBoard(newBoard);

        Members savedMember = memberService.saveMember(newMember);


        MembersBoard membersBoard = MembersBoard.builder()
                .members(savedMember)
                .board(board)
                .build();

        memberBoardService.createMemberBoard(membersBoard);
    }

}