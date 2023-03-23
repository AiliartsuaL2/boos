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
@Transactional
@Commit
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    LocalDate openDate = parse("2022-12-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @Test
    @Commit
    void save(){
            Members savedMember = Members.builder()
                    .id("aiolasrfas")
                    .businessCategory("요식업")
                    .businessRegNum("123123123")
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
    }

    @Test
    void deleteMember() {
        //given
        long seq = 2;
        //when
        memberService.deleteMember(seq);
        //then
//        memberService.findMember(seq);


    }
}