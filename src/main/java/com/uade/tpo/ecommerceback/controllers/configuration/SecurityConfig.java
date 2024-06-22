package com.uade.tpo.ecommerceback.controllers.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers("/api/v1/auth/login").permitAll()
                                                .requestMatchers("/api/v1/auth/register").permitAll()
                                                .requestMatchers("/api/v1/auth/user/change").permitAll()
                                                .requestMatchers("/categories/create").hasRole("ADMIN")
                                                .requestMatchers("/descuento/create").hasRole("ADMIN")
                                                .requestMatchers("/producto/create").hasRole("ADMIN")
                                                .requestMatchers("/producto/edit").hasRole("ADMIN")
                                                .requestMatchers("/producto/delete").hasRole("ADMIN")
                                                .requestMatchers("/api/v1/auth/clientes").hasRole("ADMIN")
                                                .requestMatchers("/api/v1/auth/delete/{userId}").hasRole("ADMIN")
                                                .requestMatchers("/shoppingCart/crear").hasRole("CLIENTE")
                                                .requestMatchers("/categories").permitAll()
                                                .requestMatchers("/categories/{categoryId}").permitAll()
                                                .requestMatchers("/ping").permitAll()
                                                .requestMatchers("/producto/all").permitAll()
                                                .requestMatchers("/producto/categoria/{idCategoria}").permitAll()
                                                .requestMatchers("/producto/{id}").permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(
                                                sessionManager -> sessionManager
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }
}
