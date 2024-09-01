package com.parfumaria.be.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.parfumaria.be.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtRequestFilter jwtFilter;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("api/user/register",
                            "api/user/auth/login",
                            "api/products/get-all-products",
                            "api/products/get-product/{id}",
                            "api/products/add-product",
                            "/v3/api-docs/**",
                            "/swagger-ui/**")
                    .permitAll()
                    .requestMatchers("/upload-user-image",
                            "/cart/**",
                            "api/auth/edit-profile",
                            "api/auth/profile")
                    .hasAuthority("USER")
                    .anyRequest().authenticated())
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
        // return http
        // .csrf(AbstractHttpConfigurer::disable)
        // .authorizeHttpRequests((auth) -> {
        // auth.requestMatchers(HttpMethod.GET, "/api/auth/me").authenticated();
        // auth.requestMatchers(HttpMethod.GET, "/api/auth/sign-out").authenticated();
        // auth.requestMatchers(HttpMethod.GET, "/api/planets").authenticated();
        // auth.anyRequest().permitAll();
        // })
        // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        // .build();
    }
}
