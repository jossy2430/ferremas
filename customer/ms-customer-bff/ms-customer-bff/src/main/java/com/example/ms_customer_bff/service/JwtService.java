package com.example.ms_customer_bff.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ms_customer_bff.modelDTO.CustomerDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(CustomerDTO customerDTO){
        return Jwts.builder()
        .setSubject(customerDTO.getCorreo())
        .claim("rol", "ROLE_USER")
        .setIssuedAt(new Date())
        .setExpiration( new Date(System.currentTimeMillis() + 86400000))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
    
    }
}
