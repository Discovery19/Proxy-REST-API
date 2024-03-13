package org.example.configuration;

import org.example.services.AuditAspect;
import org.example.repositories.AuditLogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuditAspect auditAspect(AuditLogRepository auditLogRepository) {
        return new AuditAspect(auditLogRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/albums/**").hasAnyAuthority("ADMIN", "ALBUM")
                        .requestMatchers("/api/posts/**").hasAnyAuthority("ADMIN", "POST")
                        .requestMatchers("/api/users/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/", "/registration", "/login").permitAll()
                )
                //для формирования логина без контроллера
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

        return http.build();
    }
}