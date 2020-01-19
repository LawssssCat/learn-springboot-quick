package com.edut.springboot.tarena.common.utils;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;

import com.edut.springboot.tarena.pojo.SysUser;

public abstract class ShiroUtils {

	public static String getId() {
		Subject subject = SecurityUtils.getSubject();
		if(subject instanceof WebSubject) {
			WebSubject weSubject = (WebSubject) subject ;
			ServletRequest request = weSubject.getServletRequest() ;
			if(request!=null) {
				return request.getRemoteHost()  ; 
			}
		}
		return null ; 
	}
	
	public static String getUsername() {
		Subject subject = SecurityUtils.getSubject();
		if(subject != null) {
			Object principal = subject.getPrincipal();
			if(principal instanceof SysUser) {
				SysUser user = (SysUser) principal ; 
				return  user.getUsername() ; 
			}
		}
		return null ;
	}
	
}
