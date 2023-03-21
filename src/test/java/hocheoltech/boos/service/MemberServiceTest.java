package hocheoltech.boos.service;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.repository.MembersRepository;
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
                .id("jpajpajpa")
                .businessCategory("요식업")
                .businessRegNum("123123123")
                .joinTime(LocalDateTime.now())
                .name("쥬후")
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
}