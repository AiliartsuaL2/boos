package hocheoltech.boos.controller;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.exception.DuplicateMemberIdException;
import hocheoltech.boos.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public String createMember(@RequestBody Members members){
        memberService.saveMember(members);
        return "ok";
    }

    @PostMapping("/api/v1/login")
    public String login(@RequestBody Members members){
        memberService.loginMember(members);
        return "ok";
    }

}
