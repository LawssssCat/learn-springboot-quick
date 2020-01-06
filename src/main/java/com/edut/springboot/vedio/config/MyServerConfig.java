package com.edut.springboot.vedio.config;

import java.util.Arrays;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edut.springboot.vedio.servlet.MyServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MyServerConfig {
	
	//注册三大组件
	@Bean
	public ServletRegistrationBean<MyServlet> MyServlet() {
		ServletRegistrationBean<MyServlet> registration = new ServletRegistrationBean<>();
		registration.setUrlMappings(Arrays.asList("/myservlet"));
		registration.setServlet(new MyServlet());
		return registration; 
	}
	
	
	/** 配置嵌入式的Servlet容器 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> WebServerFactoryCustomizer() {
		log.info("@@@@@@@@ ----- insert My WebServerFactoryCustomizer");
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

			/** 定义嵌入式的Servlet容器的相关规则 */
			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.setPort(8083);
			}
			
		};
	}

}
