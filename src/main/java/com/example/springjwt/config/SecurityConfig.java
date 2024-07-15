package com.example.springjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // 비밀번호 암호화
    // 회원가입 및 검증할때 비밀번호를 캐시로 암호화 시켜서 검증을 하고 진행을 한다.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 설정을 disable
        // 이유: 세션방식에서는 세션이 고정되기 때문에 csrf공격을 항상 방어해야한다.
        //      하지만 jwt방식에서는 세션을 stateless하게 사용하기 때문에 csrf공격을 방어할 필요가 없다.
        http
                .csrf((auth) -> auth.disable());
        // From 로그인 방식 disable
        // 이유: jwt방식에서는 로그인 방식을 사용하지 않기 때문에 로그인 방식을 disable한다.
        http
                .formLogin((auth) -> auth.disable());
        // http Basic 로그인 인증 방식 disable
        // 이유: jwt방식에서는 http Basic 로그인 인증 방식을 사용하지 않기 때문에 http Basic 로그인 인증 방식을 disable한다.
        http
                .httpBasic((auth) -> auth.disable());






        // 경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());



        // 세션 설정 (가장 중요)
        // 이유: jwt방식에서는 세션을 stateless하게 사용하기 때문에 세션을 사용하지 않는다.
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }


}
