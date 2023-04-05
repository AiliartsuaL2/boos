package hocheoltech.boos.jwt;

import hocheoltech.boos.exception.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//해당 클래스는 JwtTokenProvider가 검증을 끝낸 Jwt로부터 유저 정보를 조회해와서 UserPasswordAuthenticationFilter 로 전달.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, JwtException {
        // 헤더에서 JWT 를 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        // 유효한 토큰인지 확인 ,, 여기서 Throw 처리하기

        try {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response); // 걸려있는 필터를 호출 시키는 메서드
        }
        catch(SignatureException e){
            throw new JwtException(ErrorMessage.WRONG_TYPE_TOKEN.getMsg());
        }catch (MalformedJwtException e){
            throw new JwtException(ErrorMessage.UNSUPPORTED_TOKEN.getMsg());
        }catch (ExpiredJwtException e) {
            throw new JwtException(ErrorMessage.EXPIRED_TOKEN.getMsg());
        }catch(IllegalArgumentException e){
            throw new JwtException(ErrorMessage.UNKNOWN_ERROR.getMsg());

        }
    }
}
