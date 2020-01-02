package com.edut.springboot.vedio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edut.springboot.vedio.service.HelloService;

/**
 * @Configuration 指明当前类似一个配置类，就是来替代之前的spring配置文件
 * 
 * 在配置文件中使用<bean></bean>添加标签组件
 */
@Configuration
public class MyAppConfig {

	//将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
	@Bean
	public HelloService helloService() {
		return new HelloService() ; 
	}
}
