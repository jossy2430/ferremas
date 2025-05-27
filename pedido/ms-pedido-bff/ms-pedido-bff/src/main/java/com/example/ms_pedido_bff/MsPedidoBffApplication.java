package com.example.ms_pedido_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
@CrossOrigin("*")
public class MsPedidoBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPedidoBffApplication.class, args);
	}

}
