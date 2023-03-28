package hocheoltech.boos.config;


import hocheoltech.boos.jwt.JwtAuthenticationFilter;
import hocheoltech.boos.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests() // 요청에 대한 사용 권한 체크
                .antMatchers("/test").authenticated() // authenticated : andMatchers의 URL로 요청이 오면 인증이 필요하다고 설정
                .antMatchers("/admin/**").hasRole("ADMIN") // antMatchers : 해당 URL 요청시 설정해줌
                .antMatchers("/user/**").hasRole("USER")// hasRole : andMatchers 속의 URL로 요청이 들어오면 권한을 확인한다.
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), // 필터를 등록함, 파라미터 - 1번째 : 커스텀한 필터링, 2번쨰 : 필터링전 커스텀 필터링 수행
                        UsernamePasswordAuthenticationFilter.class);
        // JwtAuthenticationFilter를 UserIdPasswordAuthenticationFilter 전에 넣는다 + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않는다고 설정.
    }
}
