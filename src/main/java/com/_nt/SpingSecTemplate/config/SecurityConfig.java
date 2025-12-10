package com._nt.SpingSecTemplate.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetails;

    @ Bean
    public SecurityFilterChain SecurityFilterChain (HttpSecurity security) throws Exception{

        return security
                .csrf(customizer->customizer.disable()) // disables csrf
                .authorizeHttpRequests(req-> req
                        .requestMatchers("/login", "/register")
                        .permitAll()// permit all the route that match the above else authenticate the rest
                        .anyRequest().authenticated()) // authenticates all requests
//                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                //session creation policy helps you manage sessions
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // makes your request stateless
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetails);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(13)); // set how you want your password to be encoded like using bcrypt

        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config){
       return config.getAuthenticationManager(); //we are explicitly creating an Authmanager here so we can injest it wherever we want manually
    }

}
