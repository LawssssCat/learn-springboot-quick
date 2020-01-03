package com.edut.springboot.vedio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//super.addViewControllers(registry);
		//浏览器发送/low请求，来到success页面
		registry.addViewController("/low").setViewName("/success");
	}
	
	//所有的WebMvcConfigurerAdapter组件都会一起起作用
	@Bean //将组件注册到组件中
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				//super.addViewControllers(registry);
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/index.html").setViewName("login");
			}
		};
		return adapter ;
	}
}
