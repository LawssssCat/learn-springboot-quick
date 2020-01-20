package com.edut.springboot.tarena.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jca.cci.CannotCreateRecordException;



/**
 * @Configuration 注解描述的类为一个配置对象
 * 此对象交给spring管理
 *
 */
@Configuration //bean 
public class SpringShiroConfig {

	/**
	 * @Bean 注解一般用于描述@Configuration注解修饰的类中的方法。
	 * 目的是将方法的调用交给Spring框架
	 * 并且Spring框架管理这个方法返回值的对象
	 * (例如，将次对象存储到Spring容器，并给定作用域。 存储时默认的key为方法名)
	 * 
	 * @return SecurityManager (此对象是shiro框架的核心)
	 * 
	 * 有抽象，尽量写抽象
	 */
	@Bean("sManager")
	public SecurityManager securityManager(Realm realm) { //这里的 Realm 是我们自定义的  ShiroUserRealm
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		return sManager; 
	}
	
	
	/**
	 * 初始化过滤器工厂bean对象(底层要通过此对象创建过滤器工厂，然后通过工厂创建过滤器)
	 * 思考：
	 * 1)为什么要创建过滤器呢？(通过过滤器对象请求数据进行校验，通过过滤操作，例如检查用户是否已认证)
	 * 2)过滤器的请求或响应数据进行过滤时，要指定规则吗？(哪些请求要过滤，哪些不需要过滤？)
	 * 3)对于要通过过滤器的请求，我们通过谁对数据进行校验呢？(例如可以在SecurityManager中进行认证检测) 
	 * @param securityManager
	 * @return
	 */
	@Bean
	@Autowired //如何找到形参的实参? @Autowired + @Qualifier
	public ShiroFilterFactoryBean shiroFilterFactory(
			@Qualifier("sManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = 
				new ShiroFilterFactoryBean() ; 
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		shiroFilterFactoryBean.setLoginUrl("/doLoginUI");
		
		//定义map指定 请求过滤规则(哪些资源允许匿名访问、哪些资源必须认证访问)
		LinkedHashMap<String,String> map = new LinkedHashMap<String, String>() ; 
		//静态资源允许匿名访问:"anon" 
		//anon/əˈnɒn/匿名的
		map.put("/bower_components/**", "anon") ; 
		map.put("/build/**", "anon");
		map.put("/dist/**", "anon") ;
		map.put("/docs/**", "anon") ;
		map.put("/plugins/**", "anon") ;
		map.put("/user/doLogin", "anon") ; 
		map.put("/doLogout", "logout") ; 
		//除了匿名访问的资源，其他都要认证"authc"
		
		map.put("/**", "authc") ;
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		
		return shiroFilterFactoryBean ;  
	}
	
	@Bean//bean声明周期管理
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor() ;
	}
	
	//默认顾问代理生产者
	@Bean
	@DependsOn("lifecycleBeanPostProcessor") 
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return  advisorAutoProxyCreator ; 
	}
	
	//授权属性源顾问
	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;  
	}
	
	
	
}
