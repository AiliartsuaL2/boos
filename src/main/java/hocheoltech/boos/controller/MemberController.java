package hocheoltech.boos.controller;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/member")
    public ResponseEntity<Members> createMember(@RequestBody Members members) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        Members member = Members.builder()
                .id("jpaAiliartsua")
                .name("juholeeeee")
                .password("qwqwqwqw12")
                .nickname("ailiartsua")
                .openTime(date)
                .businessCategory("요식업")
                .businessRegNum("12345678910")
                .joinTime(LocalDateTime.now())
                .build();
        Members savedMember = memberService.saveMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

//    @PostMapping("/v1/login")
//    public String login(@RequestBody Members members){
//        memberService.loginMember(members);
//        return "ok";
//    }



}
