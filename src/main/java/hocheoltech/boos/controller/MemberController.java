package hocheoltech.boos.controller;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/member")
    public String createMember(@RequestBody Members members){
        memberService.saveMember(members);
        return "ok";
    }

    @PostMapping("/v1/login")
    public String login(@RequestBody Members members){
        memberService.loginMember(members);
        return "ok";
    }



}
