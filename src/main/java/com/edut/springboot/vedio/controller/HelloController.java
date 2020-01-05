package com.edut.springboot.vedio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//这个类的所有方法返回的数据直接写给浏览器。(如果是对象，还能转成json数据)

@Controller
//@RestController
public class HelloController {
	

	//Config 里面注册了！
//	@RequestMapping({"/","/index"})
//	public String index() {
//		return "login" ; 
//	}
	
	@RequestMapping({"/dashboard"})
	public String dashboard() {
		return "dashboard" ; 
	}

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring!" ; 
	}
	
	// RESTAPI的方式
	
	@RequestMapping("/success")
	public String success(Map<String, Object> map) {
		map.put("hello", "<h3>你好！</h3>");
		map.put("users", Arrays.asList("张珊","李四")) ; 
		return "success" ; 
	}
}
