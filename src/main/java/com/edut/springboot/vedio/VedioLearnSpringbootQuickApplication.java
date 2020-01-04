package com.edut.springboot.vedio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = "classpath:beans.xml")
@SpringBootApplication
public class VedioLearnSpringbootQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(VedioLearnSpringbootQuickApplication.class, args);
	}

}
