package com.edut.springboot.vedio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.edut.springboot.vedio.component.LoginHandlerInterceptor;
import com.edut.springboot.vedio.component.MyLocalResolver;

import lombok.extern.slf4j.Slf4j;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Slf4j
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//super.addViewControllers(registry);
		//浏览器发送/low请求，来到success页面
		registry.addViewController("/low").setViewName("/success");
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index.html").setViewName("login");
		registry.addViewController("/dashboard.html").setViewName("/dashboard.html");
	}
	
	
	//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//“/”路径下的任何目录
		String[] patterns = {"/**" };
		//静态资源: *.css *.js
		String[] exPatterns = {	"/login" , 
								"/" , 
								"/user/login" ,
								"/login.html" ,   
								"/**/*.js" , 
								"/**/*.css" , 
								"/docs/**"  
								};
		registry.addInterceptor(new LoginHandlerInterceptor())
		.addPathPatterns(patterns)
		.excludePathPatterns(exPatterns) ; 
		
		log.info("@@@@@@@@ ----- add Interceptors");
	}



	/**
	 * 嗅探所有 / or /index.html 请求 
	 */
	//所有的WebMvcConfigurerAdapter组件都会一起起作用
//	@Bean //将组件注册到组件中
//	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
//		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
//			@Override
//			public void addViewControllers(ViewControllerRegistry registry) {
//				//super.addViewControllers(registry);
//			}
//		};
//		return adapter ;
//	}
	
	@Bean
	public LocaleResolver localeResolver() {
		log.info("@@@@@@@@ ----- insert My LocaleReolver");
		return new MyLocalResolver() ; 
	}
}
