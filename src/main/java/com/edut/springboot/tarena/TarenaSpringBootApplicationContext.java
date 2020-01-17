package com.edut.springboot.tarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TarenaSpringBootApplicationContext {

	public static void main(String[] args) {
		SpringApplication.run(TarenaSpringBootApplicationContext.class, args) ; 
	}
}
