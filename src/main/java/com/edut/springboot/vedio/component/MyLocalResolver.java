package com.edut.springboot.vedio.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyLocalResolver implements LocaleResolver {

	public MyLocalResolver() {
	}

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String s = request.getParameter("l");
		Locale locale = null ; 
		if(!StringUtils.isEmpty(s)) {
			String[] split = s.split("_");
			
			if(!StringUtils.isEmpty(split)) {
				locale = new Locale(split[0], split[1]) ; 
				log.info(locale.toString());
				return locale ;
			}
		}
		return request.getLocale(); 
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// TODO Auto-generated method stub
		
	}

}
