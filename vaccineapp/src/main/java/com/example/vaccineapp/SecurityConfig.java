package com.example.vaccineapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/public/**").permitAll() // Cho phép truy cập không cần đăng nhập
            .anyRequest().authenticated() // Các yêu cầu khác phải xác thực
        )
        .formLogin(form -> form
            .loginPage("/login") // Trang đăng nhập tùy chỉnh
            .permitAll() // Cho phép tất cả truy cập trang đăng nhập
        );
        return http.build();
    }

    // Cấu hình người dùng trong bộ nhớ (in-memory) cho việc đăng nhập
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
