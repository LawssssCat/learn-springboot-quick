package com.edut.springboot.tarena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

	@RequestMapping("/doIndexUI")
	public String doIndexUI() {
		return "/starter";
	}
	
	@RequestMapping("/doPageUI")
	public String doPageUI() {
		return  "/common/page" ; 
	}
	
//	@RequestMapping("/log/log_list")
//	public String doLogUI() {
//		return "/sys/log_list" ; 
//	}
	
	/**
	 * rest风格(软件架构编码风格)的url
	 * {}为rest表达式(内容为自己定义的变量)
	 * @PathVariable 注解告诉 Spring mvc模块，方法参数的值来自url
	 */
	@RequestMapping("/{model}/{modelUI}")
	public String doModelUI(
			@PathVariable(value = "model") String model , 
			@PathVariable(value = "modelUI") String modelUI) {
		return "/"+model+"/"+ modelUI ;
	}
	
}
