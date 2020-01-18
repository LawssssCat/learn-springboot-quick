package com.edut.springboot.tarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching 
@EnableAsync  //spring容器启动时会创建线程池 (系统底层会进行异步的自动配置)
@SpringBootApplication
public class TarenaSpringBootApplicationContext {

	public static void main(String[] args) {
		SpringApplication.run(TarenaSpringBootApplicationContext.class, args) ; 
	}
}
