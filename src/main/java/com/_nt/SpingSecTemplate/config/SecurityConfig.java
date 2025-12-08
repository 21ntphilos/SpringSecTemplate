package com._nt.SpingSecTemplate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @ Bean
    public SecurityFilterChain SecurityFilterChain (HttpSecurity security) throws Exception{

        return security
                .csrf(customizer->customizer.disable()) // disables csrf
                .authorizeHttpRequests(req-> req.anyRequest().authenticated()) // authenticates all requests
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                //session creation policy helps you manage sessions
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // makes your request stateless
                .build();
    }
}
