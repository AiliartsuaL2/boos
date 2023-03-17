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
    Members members = new Members("juho","qw1621","이주호","요식업","3561184855593","20220205");

    //when
        memberService.saveMember(members);

    //then

}

}