package com.example.vaccineapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    // Tạo người dùng tĩnh trong bộ nhớ với AuthenticationManagerBuilder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
            .inMemoryAuthentication()
                .withUser(User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin123"))
                    .build());
        
        return authenticationManagerBuilder.build();
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
    	return (web)->web.debug(true).ignoring().requestMatchers("/static/**", "/img/**", "/css/**", "/js/**");
    }
    // Cấu hình PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Dùng NoOpPasswordEncoder chỉ cho mục đích phát triển
    }
}
