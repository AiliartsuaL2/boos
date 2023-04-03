package hocheoltech.boos.controller;

import hocheoltech.boos.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception")
@Slf4j
public class ExceptionController {

    @RequestMapping("/redirect")
    public ResponseEntity<ErrorMessage> redirectException(@RequestBody ErrorMessage errorMessage){
        return new ResponseEntity<ErrorMessage>(errorMessage , errorMessage.getHttpStatus());
    }
}
