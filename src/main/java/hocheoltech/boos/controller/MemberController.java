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
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

//    @PostMapping("/v1/member")
//    public ResponseEntity<Members> createMember(@RequestBody Members members) throws ParseException {
//        return new ResponseEntity<>(savedMember, HttpStatus.OK);
//    }

//    @PostMapping("/v1/login")
//    public String login(@RequestBody Members members){
//        memberService.loginMember(members);
//        return "ok";
//    }



}
