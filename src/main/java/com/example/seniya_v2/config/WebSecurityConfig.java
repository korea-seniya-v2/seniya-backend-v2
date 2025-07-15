package com.example.seniya_v2.config;

import com.example.seniya_v2.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Lazy
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v2/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v2/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v2/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v2/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v2/payments").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v2/payments/confirm/**").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.GET, "/api/v2/notices/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v2/posts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v2/courses/**").permitAll()

                        .requestMatchers("/files/**", "/api/v2/auth/**", "/api/v2/**").permitAll()

                        .requestMatchers("/favicon.ico", "/error").permitAll()


                        .requestMatchers(HttpMethod.POST, "/api/v2/notices/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v2/posts/**").hasRole("USER")

                        .requestMatchers(HttpMethod.PUT, "/api/v2/notices/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v2/notices/**").hasRole("ADMIN")

                        .requestMatchers("/api/v2/user/**").hasRole("USER")

                        .requestMatchers(HttpMethod.PUT,"/api/v2/inquiries/:id/response").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.POST, "/api/v2/trainer-profiles/me").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.GET, "/api/v2/trainer-profiles/me").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.PUT, "/api/v2/trainer-profiles/me").hasRole("TRAINER")

                        .requestMatchers(HttpMethod.DELETE, "/api/v2/users/me").authenticated()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }




    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(List.of(authProvider));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
