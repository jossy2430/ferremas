package com.example.ms_empleado_db.util; 

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "195645671";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
    }
}