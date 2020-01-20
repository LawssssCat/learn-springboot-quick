package com.edut.springboot.test;

import java.util.ArrayList;
import java.util.List;

//============
//核心业务
interface MailService {
	void send(String msg) ;
}

class DefaultMailService implements MailService {
	@Override
	public void send(String msg) {
		System.out.println(msg);
	}
}
//============================================
//扩展业务

//from class TestExtends01 

//==================================
//组合对象
class ComposeMailService implements MailService {//is a
	//has a
	private MailService mailService ; 
	private List<Interceptor> interceptors ;
	
	public ComposeMailService( MailService mailService , List<Interceptor> interceptors ) {
		this.mailService = mailService;
		this.interceptors = interceptors ; 
				
	}
	
	@Override
	public void send(String msg) {
		interceptors.forEach(i -> i.doBefore());
		
		mailService.send(msg); //核心业务
		
		interceptors.forEach(i -> i.doAfter());
	}
	
}


//======================================
public class TestCompose01 {
	public static void main(String[] args) {
		List<Interceptor> interceptors = new ArrayList<Interceptor>() ;
		interceptors.add(new PermissionInterceptor()) ;
		interceptors.add(new LogInterceptor() ) ;
		
		ComposeMailService service = new ComposeMailService( new DefaultMailService() , interceptors);
		service.send("新年快乐！");
		
	}
}
