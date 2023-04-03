package hocheoltech.boos.exception;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws RuntimeException, ServletException, IOException {
        String exception = (String)request.getAttribute("exception");

        if(exception == null) {
            setResponse(response, ErrorMessage.UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(ErrorMessage.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, ErrorMessage.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception.equals(ErrorMessage.EXPIRED_TOKEN.getCode())) {
            setResponse(response, ErrorMessage.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(ErrorMessage.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, ErrorMessage.UNSUPPORTED_TOKEN);
        }
        else {
            setResponse(response, ErrorMessage.ACCESS_DENIED);
        }
    }
    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ErrorMessage errorMessage) throws RuntimeException, IOException {
        response.setStatus(errorMessage.getCode());
        response.getWriter().print(errorMessage.getMsg());
    }
}
