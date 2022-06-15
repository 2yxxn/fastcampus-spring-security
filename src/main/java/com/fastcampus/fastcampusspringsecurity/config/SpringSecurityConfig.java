package com.fastcampus.fastcampusspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // filter
        http.httpBasic().disable();
        http.csrf();
        http.rememberMe();

        // authorization
        // "/", "/home", "signup" : 모두에게 허용
        // "/note" : 사용자에게만 허용
        // "/admin" : 관리자에게만 허용
        // "/notice" 의 POST, DELETE : 관리자에게만 허용
        // 그 외의 모든 요청은 인증을 받은 사람에게만 허용
        http.authorizeRequests()
                .antMatchers("/", "/home", "signup").permitAll()
                .antMatchers("/note").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/notice").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/notice").hasRole("ADMIN")
                .anyRequest().authenticated();

        // login
        // 로그인 페이지를 "/login" 으로 지정
        // 로그인이 성공하면 "/" 으로 이동
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll();

        // logout
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // spring security 에서 h2 를 쓰기 위해 "/error", "/favicon", "/h2-console/**" 추가

        return web -> web.ignoring()
                .antMatchers("/images/**", "/css/**", "/error", "/favicon", "/h2-console/**");
    }

    // TODO: UserService 코드 작성 후 마저 작성
    // username 으로 user 찾는 방법
    @Bean
    public UserDetailsService userDetailsService() {
        return null;
    }
}
