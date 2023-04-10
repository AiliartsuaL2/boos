package hocheoltech.boos.service;

import hocheoltech.boos.domain.BusinessCategory;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.members.MembersJoinDto;
import hocheoltech.boos.dto.members.MembersLoginDto;
import hocheoltech.boos.dto.members.UpdateMembersDto;
import hocheoltech.boos.repository.BusinessCategoryRepository;
import hocheoltech.boos.repository.MembersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Commit
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MembersRepository membersRepository;

    @Autowired
    BusinessCategoryRepository businessCategoryRepository;


    @Test
    void save(){

        LocalDate openTime = LocalDate.parse("20230410", DateTimeFormatter.ofPattern("yyyyMMdd"));

        MembersJoinDto build = MembersJoinDto.builder()
                .id("ailiartsua")
                .name("이주호")
                .password("qw1621")
                .businessCategory("쇼핑몰")
                .businessRegNum("123456789")
                .openTime(openTime)
                .build();
        MembersJoinDto membersJoinDto = memberService.saveMember(build);
        assertThat(membersJoinDto.getId()).isEqualTo("ailiartsua");
    }

    @Test
    @Transactional
    void findMember() {
    }

    @Test
    void deleteMember() {
        //given
        String id = "ailiartsua";
        MembersLoginDto mdto = MembersLoginDto.builder()
                .id(id)
                .password("qw1621")
                .build();


        //when
        memberService.deleteMember(mdto);

        //then
        Members member = membersRepository.findById(id).get();
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
        Members member = membersRepository.findById("cy123").get();
        //then
        assertThat(member.getPassword()).isEqualTo("qw1621");

    }
}