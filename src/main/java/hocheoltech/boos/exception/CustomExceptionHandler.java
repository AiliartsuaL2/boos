package hocheoltech.boos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice("hocheoltech.boos.controller") // exception 스코프를 패키지 레벨로
public class CustomExceptionHandler {
    @ExceptionHandler(DuplicateIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String DuplicateIdException(String message) {
        return message;
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String NoSuchElementInfoException(String message) {
        return message;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String IllegalArgumentException(String message) {
        return message;
    }

}
