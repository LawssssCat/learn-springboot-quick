package com.edut.springboot.vedio.config;

import java.util.Arrays;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edut.springboot.vedio.filter.MyFilter;
import com.edut.springboot.vedio.listener.MyEventListener;
import com.edut.springboot.vedio.servlet.MyServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MyServerConfig {
	
	//注册三大组件
	/** servlet */
	@Bean
	public ServletRegistrationBean<MyServlet> myServlet() {
		ServletRegistrationBean<MyServlet> registration = new ServletRegistrationBean<>();
		registration.setUrlMappings(Arrays.asList("/myservlet"));
		registration.setServlet(new MyServlet());
		
		log.info("@@@@@@@@ ----- insert My Servlet");
		return registration; 
	}
	/** 拦截器 */
	@Bean
	public FilterRegistrationBean<MyFilter> myFilter() {
		FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<MyFilter>();
		//filter
		registration.setFilter(new MyFilter());
		//拦截地址
		registration.setUrlPatterns(Arrays.asList());
		log.info("@@@@@@@@ ----- insert My filter");
		return registration ; 
	}
	
	@Bean
	public ServletListenerRegistrationBean<MyEventListener> myListener() {
		ServletListenerRegistrationBean<MyEventListener> registration = 
				new ServletListenerRegistrationBean<MyEventListener>(
						new MyEventListener());
		log.info("@@@@@@@@ ----- insert My eventListener");
		return registration ; 
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
