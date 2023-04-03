package hocheoltech.boos.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hocheoltech.boos.exception.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        try{
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            //만료 에러
            request.setAttribute("exception", ErrorMessage.EXPIRED_TOKEN.getCode());

        } catch (MalformedJwtException e){
            //변조 에러
            request.setAttribute("exception", ErrorMessage.WRONG_TYPE_TOKEN.getCode());

        } catch (SignatureException e){
            //형식, 길이 에러
            request.setAttribute("exception", ErrorMessage.WRONG_TYPE_TOKEN.getCode());
        }
    }
}
