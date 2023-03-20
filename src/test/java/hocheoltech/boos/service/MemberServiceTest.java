package hocheoltech.boos.service;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.repository.MembersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    
    @Autowired
    MembersRepository membersRepository;


//    @Test
//void save() {
//	//given
//    Members members = new Members();
//    members.setId("sadfgvs");
//    members.setName("jpa이주옹");
//    members.setPassword("q12weqe");
//    members.setNickname("ailasrjpa");
//    members.setBusinessRegNum("123123123");
//    members.setBusinessCategory("사업");
//    //when
//        memberService.saveMember(members);
//    //then
//}
//
//    @Test
//    @Transactional
//    void save() throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = format.parse("2011-12-03");
//        Members member = Members.builder()
//                .id("jpaAiliartsua324")
//                .name("juholeeeee")
//                .password("qwqwqwqw12")
//                .nickname("ailiartsua")
//                .openTime(date)
//                .businessCategory("요식업")
//                .businessRegNum("12345678910")
//                .joinTime(LocalDateTime.now())
//                .build();
//        Members savedMember = memberService.saveMember(member);
//    }

@Test
void getMember(){
        //given
        
        //when
        
        //then

    List<Members> all = membersRepository.findAll();
    for (Members members : all) {
        System.out.println("members = " + members.getName());
    }
}

}