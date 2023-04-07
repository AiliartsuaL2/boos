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

        for (int i = 3; i < 50; i++) {
            MembersJoinDto m = MembersJoinDto.builder()
                    .id("luvsole" + i)
                    .businessCategory("쇼핑몰")
                    .businessRegNum("5678910212")
                    .name("김정아")
                    .nickname("luvsole" + i)
                    .openTime("20221206")
                    .password("123123")
                    .build();
            memberService.saveMember(m);
        }
    }

    @Test
    @Transactional
    void findMember() {
    }

    @Test
    void deleteMember() {
        //given
        String id = "cy123";
        MembersLoginDto mdto = MembersLoginDto.builder()
                .id(id)
                .password("qw1621")
                .build();


        //when
        memberService.deleteMember(mdto);

        //then
        Members member = membersRepository.findById("cy123").get();
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