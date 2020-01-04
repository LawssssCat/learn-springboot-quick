package com.edut.springboot.vedio.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "username") String username , 
			@RequestParam(name = "password") String password , Map<String, Object> map ) {
		if(!StringUtils.isEmpty(username) && "123".equals(password)) {
			return "dashboard";
		}else {
			map.put("msg", "密码错误或账号不存在！") ;
			return "login";
		}
	}

}
