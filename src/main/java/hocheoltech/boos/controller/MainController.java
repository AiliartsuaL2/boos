package hocheoltech.boos.controller;

import hocheoltech.boos.domain.Members;
import hocheoltech.boos.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @PostMapping("/member")
    @ResponseBody
    public String createMember(@RequestBody Members members){
        memberService.saveMember(members);
        return "ok";
    }

}
