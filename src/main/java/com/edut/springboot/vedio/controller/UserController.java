package com.edut.springboot.vedio.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate ; 
	
	@RequestMapping("/test")
	@ResponseBody
	public List<Map<String, Object>> test1() {
		String sql = "select * from sys_configs";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql );
		list.forEach(l -> log.info(l.toString()));
		return list ; 
	}
	
	
	/**
	 * 记录登录 session
	 */
	@PostMapping("/login")
	public String login(@RequestParam(name = "username") String username , 
						@RequestParam(name = "password") String password , 
						Map<String, Object> map, 
						HttpSession session 
						) 
	{
		if(!StringUtils.isEmpty(username) && "123".equals(password)) {
			session.setAttribute("loginUsername", username);
			return "redirect:/main";
		}else {
			map.put("msg", "密码错误或账号不存在！") ;
			return "login";
		}
	}

}
