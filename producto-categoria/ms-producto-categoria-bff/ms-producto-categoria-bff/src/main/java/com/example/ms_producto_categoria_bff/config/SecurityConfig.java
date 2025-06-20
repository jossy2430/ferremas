package com.example.ms_producto_categoria_bff.config;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final String SECRET_KEY = "thisIsAStrongAndSecureSecretKeyForJwtTokenGeneration123";

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecretKey secretKey) throws Exception {
        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(secretKey);

        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/auth/registro", "/auth/login").permitAll() // Rutas públicas
            .requestMatchers("/api/categorias", "/api/productos/**").permitAll() // Rutas públicas para ver categorías y productos
            .requestMatchers("/api/categorias/agregar", "/api/categorias/actualizar/**", "/api/categorias/eliminar/**").hasRole("ADMINISTRADOR") // Solo ADMIN puede modificar categorías
            .requestMatchers("/api/productos/agregar", "/api/productos/actualizar/**", "/api/productos/eliminar/**").hasRole("ADMINISTRADOR") // Solo ADMIN puede modificar productos
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}