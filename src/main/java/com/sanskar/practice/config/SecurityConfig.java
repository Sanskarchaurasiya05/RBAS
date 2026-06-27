package com.sanskar.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http)throws Exception{
        http.csrf(csrf-> csrf.disable())
        .authorizeHttpRequests(auth->auth
                .requestMatchers("/employee/login","/employee").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        // 1. Pass userDetailsService directly into the constructor!
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        // 2. We no longer need provider.setUserDetailsService()

        // 3. Keep the password encoder setter
        provider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(provider);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
