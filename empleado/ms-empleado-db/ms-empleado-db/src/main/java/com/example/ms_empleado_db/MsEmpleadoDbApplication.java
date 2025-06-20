package com.example.ms_empleado_db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder; // Importa el PasswordEncoder

import com.example.ms_empleado_db.model.Empleado;
import com.example.ms_empleado_db.repository.EmpleadoRepository;

@SpringBootApplication
public class MsEmpleadoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEmpleadoDbApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAdmin(EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder){ // Recibe PasswordEncoder
        return args -> {
            if (empleadoRepository.findByUsuario("ADMIN").isEmpty()){
                Empleado admin = new Empleado();
                admin.setUsuario("ADMIN");
                admin.setPassword(passwordEncoder.encode("195645671")); // Encripta la contraseña
                admin.setRol("ADMINISTRADOR");
                admin.setPrimerLogin(true);
                admin.setNombres("Joselyn Gallardo");
                admin.setApellidos("Gallardo Zapata");
                admin.setRut("19.564.567-1");
                empleadoRepository.save(admin);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

}