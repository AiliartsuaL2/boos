package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.repository.MembersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Commit
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    LocalDate openDate = parse("2022-12-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @Test
    @Transactional
    void save(){
        Members savedMember = Members.builder()
                .id("abce")
                .businessCategory("요식업")
                .businessRegNum("123123123")
                .joinTime(LocalDateTime.now())
                .name("2쥬후")
                .nickname("AIliartsua")
                .openTime(openDate)
                .password("123123")
                .build();
        memberService.saveMember(savedMember);
    }

    @Test
    void successLogin() {
        //given
        Members correctMember = Members.builder()
                .id("jpajpajpa")
                .password("123123")
                .build();
        //when
        memberService.loginMember(correctMember);

        //then

    }
    @Test
    void failedLogin(){
        //given
        Members inCorrectMember = Members.builder()
                .id("jpajpajpa")
                .password("1231234")
                .build();
        //when
        memberService.loginMember(inCorrectMember);
        //then

    }

    @Test
    @Transactional
    void findMember() {
        Members member = memberService.findMember(2L);
        System.out.println("-----------------------------1"); // 지연로딩으로 앞에선 프록시 객체만 조회하고
        List<Board> boards = member.getBoards(); // 이 땐 프록시이고
        System.out.println("-----------------------------2"); // 지연로딩으로 앞에선 프록시 객체만 조회하고
        for (Board board : boards) { //이렇게 실제로 값을 꺼내올 때 프록시 객체를 초기화하며 쿼리를날림
            System.out.println("-----------------------------3"); // 지연로딩으로 앞에선 프록시 객체만 조회하고
            String content = board.getContent();
            System.out.println("content = " + content);
        }
        System.out.println("-----------------------------4");
    }

    @Test
    void deleteMember() {
        //given
        long seq = 3;
        //when
        memberService.deleteMember(seq);
        //then
//        memberService.findMember(seq);


    }
}