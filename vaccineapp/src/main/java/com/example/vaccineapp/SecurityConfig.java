package com.example.vaccineapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/public/**", "/login").permitAll() // Cho phép truy cập công khai
                .anyRequest().authenticated() // Các request khác cần xác thực
            )
            .formLogin(form -> form
                .loginPage("/login") // Trang đăng nhập tùy chỉnh
                .defaultSuccessUrl("/home", true) // Chuyển đến trang home sau khi đăng nhập thành công
                .failureUrl("/login?error=true") // Xử lý lỗi đăng nhập
                .permitAll() // Cho phép truy cập trang đăng nhập
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true") // Chuyển hướng sau khi đăng xuất
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder); // Sử dụng mã hóa mật khẩu với BCrypt

        return authenticationManagerBuilder.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web)->web.debug(true).ignoring().requestMatchers("/static/**", "/img/**", "/css/**", "/js/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Mã hóa mật khẩu bằng BCrypt
    }
}
