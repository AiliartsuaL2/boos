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
                .businessCategory("요식업")
                .businessRegNum("123123123")
                .id("jpajpajpa")
                .joinTime(LocalDateTime.now())
                .name("쥬후")
                .nickname("AIliartsua")
                .openTime(openDate)
                .password("123123")
                .build();
        memberService.saveMember(savedMember);
    }

}