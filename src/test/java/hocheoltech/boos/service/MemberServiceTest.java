package hocheoltech.boos.service;

import hocheoltech.boos.domain.Members;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Test
void save() {
	//given
    Members members = new Members();
    members.setId("sadf");
    members.setName("이주옹");
    members.setPassword("qweqe");
    members.setNickname("ailasr");
    members.setBusinessRegNum("123123123");
    members.setBusinessCategory("사업");
    //when
        memberService.saveMember(members);
    //then

}

}