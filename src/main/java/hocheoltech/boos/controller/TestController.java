package hocheoltech.boos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @PostMapping("/test")
    public String test(){
        return "<h1>test 통과</h1>";
    }
    @GetMapping("/member/login")
    public String loginPage(){
        return "/member/login.html";
    }
    @GetMapping("/oauth/kakao/callback")
    @ResponseBody
    public String kakaoTest(String code){
        return "인증완료 코드값 : "+code;
    }
}
