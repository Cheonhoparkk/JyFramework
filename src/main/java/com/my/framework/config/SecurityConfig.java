package com.my.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. 요청에 대한 권한 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 정적 리소스는 누구나 접근 가능
                .requestMatchers("/login", "/join").permitAll() // 로그인, 회원가입 페이지는 누구나 접근 가능
                .anyRequest().authenticated() // ★ 그 외 모든 페이지(메인 포함)는 로그인해야만 접근 가능
            )
            // 2. 로그인 설정
            .formLogin(login -> login
                .loginPage("/login") //  로그인 페이지
                .loginProcessingUrl("/login-proc") // (중요) HTML 폼의 action 주소와 맞춰야 함
                .defaultSuccessUrl("/", true) // 로그인 성공하면 메인으로 이동
                .permitAll()
            )
            // 3. 로그아웃 설정
            .logout(logout -> logout
                .logoutSuccessUrl("/login") // 로그아웃 하면 로그인 페이지로 이동
                .invalidateHttpSession(true) // 세션 날리기
            )
            // 4. CSRF 보안 설정 (개발 중에는 귀찮으니까 잠깐 꺼둠)
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // 비밀번호 암호화 도구 (이게 없으면 에러 날 수 있음)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}