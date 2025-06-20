package com.example.ms_carrito_compra_bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.ms_carrito_compra_bff.security.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // CONSTANTES PARA ROLES
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_CLIENTE = "CLIENTE";
    private static final String ROLE_VENDEDOR = "VENDEDOR";
    private static final String ROLE_BODEGUERO = "BODEGUERO";

    // CONSTANTES PARA ENDPOINTS
    private static final String API_AUTH_PATTERN = "/api/auth/**";
    private static final String API_TEST_PATTERN = "/api/test/**";
    private static final String API_CARRITO_ADMIN_PATTERN = "/api/carrito/admin/**";
    private static final String API_CARRITO_MI_CARRITO = "/api/carrito/mi-carrito";
    private static final String API_CARRITO_AGREGAR_PATTERN = "/api/carrito/*/agregar-producto";
    private static final String API_CARRITO_ELIMINAR_PATTERN = "/api/carrito/*/eliminar-producto/*";
    private static final String API_CARRITO_VACIAR_PATTERN = "/api/carrito/*/vaciar";
    private static final String API_CARRITO_PATTERN = "/api/carrito/**";
    private static final String API_ITEMCARRITO_PATTERN = "/api/itemcarrito/**";

    // CONSTRUCTOR INJECTION
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(API_AUTH_PATTERN).permitAll()
                .requestMatchers(API_TEST_PATTERN).permitAll()
                .requestMatchers(API_CARRITO_ADMIN_PATTERN).hasRole(ROLE_ADMIN)
                .requestMatchers(API_CARRITO_MI_CARRITO).hasRole(ROLE_CLIENTE)
                .requestMatchers(API_CARRITO_AGREGAR_PATTERN).hasRole(ROLE_CLIENTE)
                .requestMatchers(API_CARRITO_ELIMINAR_PATTERN).hasRole(ROLE_CLIENTE)
                .requestMatchers(API_CARRITO_VACIAR_PATTERN).hasRole(ROLE_CLIENTE)
                .requestMatchers(API_CARRITO_PATTERN).authenticated()
                .requestMatchers(API_ITEMCARRITO_PATTERN).hasAnyRole(ROLE_ADMIN, ROLE_VENDEDOR, ROLE_BODEGUERO)
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}