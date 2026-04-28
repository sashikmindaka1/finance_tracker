package com.example.financial_tracker.config;

import org.springframework.context.annotation.Bean; // මේක අනිවාර්යයෙන් ඕනේ
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // මේක දැම්මම තමයි Spring මේ Config එක අඳුරගන්නේ
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API වලට මේක disable කරන එක හරි
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // මුලට '/' එක දාන්න
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean // මේකත් Bean එකක් විදිහට තියෙන්න ඕනේ
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}