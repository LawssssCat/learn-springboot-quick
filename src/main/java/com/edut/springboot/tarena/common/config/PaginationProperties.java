package com.edut.springboot.tarena.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter //set方法必须要有
@Configuration //描述此类为配置类，交给spring管理
@ConfigurationProperties(prefix = "page.config") //告诉springboot读配置文件中指定前缀里面的内容
public class PaginationProperties {
	private Integer pageSize = 10 ; 
}
