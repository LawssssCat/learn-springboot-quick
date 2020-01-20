package com.edut.springboot.test;

import java.util.ArrayList;
import java.util.List;

/**
 * /Springmvn - DispatcherServlet (简化 - 模仿)
 */


//查询服务
interface SearchService{
	Object doSearch(String key ) ; 
}

class DefaultSearchService implements SearchService {

	@Override
	public Object doSearch(String key) {
		//参数校验
		//数据访问
		//结果处理 
		System.out.println("search by "+key);
		return null;
	}
}

interface Interceptor{//扩展业务拦截
	void doBefore() ;//{} ;jdk8 不允许 接口有 方法体 
	void doAfter() ;// {} ; jdk9 允许
}

class LogInterceptor implements Interceptor { //日志业务
	@Override
	public void doBefore() {
		System.out.println("start - [Interceptor] :"+System.currentTimeMillis());
	}
	@Override
	public void doAfter() {
		System.out.println("end - [Interceptor] :"+System.currentTimeMillis());		
	}
}

class PermissionInterceptor  implements Interceptor {//验证业务
	@Override
	public void doBefore() {
		System.out.println("check permission - Permission[Interceptor]");
	}
	@Override
	public void doAfter() {
	}
}

//.....
//日志扩展：计时
class ChildDefaultSearchService extends DefaultSearchService {
	
	private List<Interceptor> interceptors ; 
	
	
	public ChildDefaultSearchService(List<Interceptor> interceptors) {
		this.interceptors = interceptors ; 
	}
	
	@Override
	public Object doSearch(String key) {
		interceptors.forEach(i -> i.doBefore());
		
		System.out.println("start - ChildDefault[SearchService] :"+ System.currentTimeMillis());
		Object result = super.doSearch(key);//目标方法
		System.out.println("end - ChildDefault[SearchService] :"+ System.currentTimeMillis());
		
		interceptors.forEach(i -> i.doAfter());
		return result;
	}
}

//权限扩展：检查权限
class PermissionLogDefaultSearchService extends ChildDefaultSearchService{
	
	public PermissionLogDefaultSearchService(List<Interceptor> interceptors) {
		super(interceptors);
	}


	@Override
	public Object doSearch(String key) {
		System.out.println("check permission - PermissionLogDefault[SearchService]");
		return super.doSearch(key);
	}
}

public class TestExtends01 {
	public static void main(String[] args) {
		List<Interceptor> interceptors = new ArrayList<>() ;
		interceptors.add(new PermissionInterceptor()) ; 
		interceptors.add(new LogInterceptor()) ; 
		
		PermissionLogDefaultSearchService service = new PermissionLogDefaultSearchService(interceptors);
		service.doSearch("!!!!!! TestExtends01 !!!!!!!") ; 
	}
}
