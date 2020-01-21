package com.edut.springboot.tarena.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edut.springboot.tarena.common.web.TimeAccessInterceptor;

import lombok.Setter;

@Setter
@ConfigurationProperties("web-service")
@Configuration
public class WebServiceConfig implements WebMvcConfigurer {

	private Integer startServiceHour = 9 ; 
	private Integer endServiceHour = 17 ; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor interceptor = new TimeAccessInterceptor(startServiceHour, endServiceHour);
		registry.addInterceptor(interceptor ).addPathPatterns("/user/doLogin") ; //转跳
	}
}
