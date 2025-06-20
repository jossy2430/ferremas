package com.example.ms_empleado_bff.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import java.io.IOException;

@Component
public class RoleAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        String path = request.getRequestURI();
        String method = request.getMethod();
        
        // Si es un endpoint público, continuar
        if (isPublicEndpoint(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated()) {
            String token = extractTokenFromRequest(request);
            
            if (token != null) {
                String role = jwtUtil.extractRole(token);
                
                // Verificar permisos según el rol y endpoint
                if (hasPermission(path, method, role)) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("{\"error\":\"No tienes permisos para acceder a este recurso\"}");
                    return;
                }
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/auth/");
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean hasPermission(String path, String method, String role) {
        // ADMINISTRADOR tiene acceso completo
        if ("ADMINISTRADOR".equals(role)) {
            return true;
        }
        
        // VENDEDOR solo puede leer empleados
        if ("VENDEDOR".equals(role)) {
            return path.startsWith("/api/empleados") && "GET".equals(method);
        }
        
        // CONTADOR puede leer y crear empleados
        if ("CONTADOR".equals(role)) {
            if (path.startsWith("/api/empleados")) {
                return "GET".equals(method) || "POST".equals(method);
            }
        }
        
        return false;
    }
}