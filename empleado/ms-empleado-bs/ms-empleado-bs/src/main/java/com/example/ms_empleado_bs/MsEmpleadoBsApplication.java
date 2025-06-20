package com.example.ms_empleado_bs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MsEmpleadoBsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEmpleadoBsApplication.class, args);
    }

}