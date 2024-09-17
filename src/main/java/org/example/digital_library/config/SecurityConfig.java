package org.example.digital_library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers ->
                        headers
                                .contentSecurityPolicy(csp ->
                                        csp.policyDirectives("default-src 'self'; script-src 'self'; object-src 'none';")
                                )
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
