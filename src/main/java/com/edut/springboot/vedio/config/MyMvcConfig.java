package com.edut.springboot.vedio.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.edut.springboot.vedio.component.LoginHandlerInterceptor;
import com.edut.springboot.vedio.component.MyErrorAttributes;
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
		//registry.addViewController("/low").setViewName("/success");
		registry.addViewController("/").setViewName("/login");
		registry.addViewController("/login").setViewName("/login");
		registry.addViewController("/main").setViewName("/main.html");
	}
	
	
	//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor())
		//“/”路径下的任何目录
		.addPathPatterns("/**")
		//排除
		.excludePathPatterns(	//登录需要
								"/login" , 
								"/" , 
								"/user/login" ,
								//静态资源: *.css *.js
								"/**/*.js" , 
								"/**/*.css" , 
								"/docs/**"  ) ; 
		
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
	
	@Bean
	public ErrorAttributes errorAttributes() {
		log.info("@@@@@@@@ ----- insert My ErrorAttributes");
		return new MyErrorAttributes() ; 
	}
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> WebServerFactoryCustomizer() {
		log.info("@@@@@@@@ ----- insert My WebServerFactoryCustomizer");
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.setPort(8083);
			}
			
		};
	}
	
}
