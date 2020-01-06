package com.edut.springboot.vedio.component;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

public class MyErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
		map.put("company", "lasssscat") ; 
		map.remove("trace") ; 

		
		map.put("ext", webRequest.getAttribute("ext", 0)); 
		
		return map;  
	}
	
}
