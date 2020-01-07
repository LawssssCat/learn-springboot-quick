package com.edut.springboot.vedio.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object username = request.getSession(true).getAttribute("loginUsername");
		
		if(username==null) {
			log.info("@@@@@@@@ ----- username:["+username+"], requestUrl:["+request.getRequestURL()+"].");
			//没有登录
			request.setAttribute("msg", "您还没有登录！");
			request.getRequestDispatcher("/").forward(request, response);
			return false ; 
		}else {
			//登录了
			return true ; 
		}
	}
	
	


}
