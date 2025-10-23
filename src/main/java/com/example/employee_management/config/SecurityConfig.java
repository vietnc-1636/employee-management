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
     * Configures the application's security filter chain.
     * This configuration:
     * Permits all HTTP requests without requiring authentication.
     * Disables CSRF protection for simplicity.
     * Disables form-based login.
     *
     * @param http the {@link HttpSecurity} to modify
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()) // Allow all requests without authentication
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .formLogin(form -> form.disable()); // Disable form login for simplicity

        return http.build();
    }
}
