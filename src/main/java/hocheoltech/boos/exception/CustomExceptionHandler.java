package hocheoltech.boos.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;



@Slf4j
@RestControllerAdvice("hocheoltech.boos.controller") // exception 스코프를 패키지 레벨로
public class CustomExceptionHandler {
    @ExceptionHandler(RejectedExecutionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String RejectedExecutionException(RejectedExecutionException ex) {
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        String message = ex.getMessage();
        log.error(message,stackTraceElements[0]);
        return message;
    }


    @ExceptionHandler(NoSuchElementException.class)
    public String NoSuchElementException(NoSuchElementException ex,ErrorMessage errorMessage) {
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        String message = errorMessage.getMsg();
        log.error(message,stackTraceElements[0]);
        return message;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String IllegalArgumentException(IllegalArgumentException ex,ErrorMessage errorMessage) {
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        String message = errorMessage.getMsg();
        log.error(message,stackTraceElements[0]);
        return message;
    }

}
