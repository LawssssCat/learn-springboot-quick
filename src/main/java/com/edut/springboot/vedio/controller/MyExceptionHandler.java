package com.edut.springboot.vedio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edut.springboot.vedio.exception.NoSuchUserException;

@ControllerAdvice
public class MyExceptionHandler {

//这样写，浏览器、其他客户端都只返回json数据
//	@ResponseBody
//	@ExceptionHandler(NoSuchUserException.class)
//	public Map<String, Object> handleException(Exception e) {
//		Map<String , Object> map = new HashMap<String, Object>() ;
//		map.put("code", "no such user ... "); 
//		map.put("message", e.getMessage()); 
//		return map ; 
//	}
	
	
	@ExceptionHandler(NoSuchUserException.class)
	public String handleException(Exception e ,HttpServletRequest request) {
		//传入我们自己的错误状态码 4xx 5xx
		/*
		 * 	protected HttpStatus getStatus(HttpServletRequest request) {
		 * Integer statusCode = 
		 * 			(Integer) request.getAttribute("javax.servlet.error.status_code");
		 */
		request.setAttribute("javax.servlet.error.status_code", 400);
		
		Map<String , Object> map = new HashMap<String, Object>() ;
		map.put("code", "no such user ... "); 
		map.put("message", e.getMessage());
		request.setAttribute("ext", map);
		
		return "forward:/error"; 
	}
	
}
