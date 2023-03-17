package hocheoltech.boos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("hocheoltech.boos.controller") // exception 스코프를 패키지 레벨로
public class CustomExceptionHandler {

    @ExceptionHandler(DuplicateMemberIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String DuplicateMemberIdException() {
        return "중복된 아이디입니다";
    }

    @ExceptionHandler(NoMatchedMemberInfoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String NoMatchedMemberInfoException() {
        return "아이디 또는 비밀번호가 일치하지 않습니다.";
    }

}
