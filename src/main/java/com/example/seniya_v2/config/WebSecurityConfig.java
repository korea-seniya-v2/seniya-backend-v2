package com.example.seniya_v2.config;

import com.example.seniya_v2.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/payments").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/payments/confirm/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/notices/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/courses/**").permitAll()

                        .requestMatchers("/files/**", "/api/v1/auth/**", "/api/v1/**").permitAll()

                        .requestMatchers("/favicon.ico", "/error").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/notices/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/posts/**").hasRole("USER")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/notices/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/notices/**").hasRole("ADMIN")

                        .requestMatchers("/api/v1/users/**").hasRole("USER")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/inquiries/:id/response").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/trainer-profiles/me").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/trainer-profiles/me").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/trainer-profiles/me").hasRole("TRAINER")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/me").authenticated()
                        .anyRequest().authenticated()

                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
