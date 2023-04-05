package hocheoltech.boos.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hocheoltech.boos.exception.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (JwtException ex) {
            String message = ex.getMessage();
            if(ErrorMessage.UNKNOWN_ERROR.getMsg().equals(message)) {
                setResponse(response, ErrorMessage.UNKNOWN_ERROR);
            }
            //잘못된 타입의 토큰인 경우
            else if(ErrorMessage.WRONG_TYPE_TOKEN.getMsg().equals(message)) {
                setResponse(response, ErrorMessage.WRONG_TYPE_TOKEN);
            }
            //토큰 만료된 경우
            else if(ErrorMessage.EXPIRED_TOKEN.getMsg().equals(message)) {
                setResponse(response, ErrorMessage.EXPIRED_TOKEN);
            }
            //지원되지 않는 토큰인 경우
            else if(ErrorMessage.UNSUPPORTED_TOKEN.getMsg().equals(message)) {
                setResponse(response, ErrorMessage.UNSUPPORTED_TOKEN);
            }
            else {
                setResponse(response, ErrorMessage.ACCESS_DENIED);
            }
        }
    }

    private void setResponse(HttpServletResponse response, ErrorMessage errorMessage) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorMessage.getCode());
        response.getWriter().print(errorMessage.getMsg());
    }
}

