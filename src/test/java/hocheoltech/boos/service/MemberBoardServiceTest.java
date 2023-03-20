package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import hocheoltech.boos.domain.MemberBoard;
import hocheoltech.boos.domain.Members;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class MemberBoardServiceTest {
    @Autowired
    MemberBoardService memberBoardService;

    @Test
    @Transactional
    void save(){

        Members members = Members.builder()
                .id("jpajpajpa")
                .name("이증오")
                .password("qwqwqw1212")
                .nickname("asdfsadf")
                .joinTime(LocalDateTime.now())
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

        MemberBoard memberBoard = MemberBoard.builder()
                .members(members)
                .board(board)
                .build();

        memberBoardService.createMemberBoard(memberBoard);
    }

}