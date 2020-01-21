package com.edut.springboot.tarena.common.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.edut.springboot.tarena.common.exception.ServiceException;

public class TimeAccessInterceptor  implements HandlerInterceptor {
	
	private Integer startService =  9   ; 
	private Integer endService = 17 ; 
	
	public TimeAccessInterceptor(Integer startService , Integer  endService) {
		this.startService = startService ; 
		this.endService = endService ; 
	}
	

	//https://blog.csdn.net/weixin_41767154/article/details/84648873
	
	//请求处理前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Calendar c = Calendar.getInstance(); //日历对象
		c.set(Calendar.HOUR_OF_DAY, startService);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		long start = c.getTimeInMillis() ; //起始的访问时间
		
		c.set(Calendar.HOUR_OF_DAY, endService);
		long end = c.getTimeInMillis() ; 
		
		long n = System.currentTimeMillis() ; 
		
		if(n<start || n>end )
			throw new ServiceException("服务开启时间:"+startService+":00 ~ "+endService+":00") ; 
			
		return true ;
	}

	//DispatchServlet - 视图前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	//请求处理后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	

}
