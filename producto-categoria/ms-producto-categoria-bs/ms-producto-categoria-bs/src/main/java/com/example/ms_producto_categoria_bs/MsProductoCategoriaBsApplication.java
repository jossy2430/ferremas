package com.example.ms_producto_categoria_bs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsProductoCategoriaBsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductoCategoriaBsApplication.class, args);
	}

}
