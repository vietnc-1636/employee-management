package com.example.employee_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure Security to allow access to all endpoints without authentication.
     * In production, you should configure appropriate authentication and
     * authorization.
     *
     * @param http HttpSecurity object
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for REST API
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().permitAll() // Allow all requests
                );

        return http.build();
    }
}
