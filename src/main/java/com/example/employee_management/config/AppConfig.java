package com.example.employee_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    /**
     * Bean to encode passwords using the BCrypt algorithm.
     * BCrypt automatically adds salt and has high complexity, suitable for storing
     * passwords.
     *
     * @return PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean to map between objects (DTO <-> Entity).
     * ModelMapper helps convert data between layers easily.
     *
     * @return ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Additional custom mappings can be configured if needed
        // modelMapper.getConfiguration()
        // .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
