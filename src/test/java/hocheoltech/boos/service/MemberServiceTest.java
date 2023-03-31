package hocheoltech.boos.service;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.UpdateMembersDto;
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
    void save(){
        for (int i = 3; i < 50; i++) {
            Members savedMember = Members.builder()
                    .id("luvsole"+i)
                    .businessCategory("쇼핑몰")
                    .businessRegNum("5678910212")
                    .name("김정아")
                    .nickname("luvsole"+i)
                    .openTime(openDate)
                    .password("123123")
                    .build();
            memberService.saveMember(savedMember);

        }
    }

    @Test
    @Transactional
    void findMember() {
    }

    @Test
    void deleteMember() {
        //given
        long seq = 106;
        memberService.deleteMember(seq);


        //when
        //then
//        memberService.findMember(seq);
    }
    @Test
    void updateMember(){
        //given
        long seq = 2;
        //when
        UpdateMembersDto updateMembersDto = UpdateMembersDto.builder()
                .id("ailiartsua")
                .password("qw1621")
                .nickname("nyeonge")
                .build();
        memberService.modifyMember(updateMembersDto);
        Members member = memberService.findMember("ailiartsua");
        //then
        assertThat(member.getPassword()).isEqualTo("qw1621");

    }
}