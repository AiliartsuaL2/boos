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

    @Test
    @Transactional
    void save(){

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

        memberBoardService.createMemberBoard(membersBoard);
    }

}