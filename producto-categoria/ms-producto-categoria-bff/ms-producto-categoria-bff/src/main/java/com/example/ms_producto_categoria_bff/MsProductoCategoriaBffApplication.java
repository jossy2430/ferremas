package com.example.ms_producto_categoria_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsProductoCategoriaBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductoCategoriaBffApplication.class, args);
	}

}
