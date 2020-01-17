package com.edut.springboot.tarena.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edut.springboot.tarena.common.annotation.RequiredLog;
import com.edut.springboot.tarena.dao.SysLogDao;
import com.edut.springboot.tarena.pojo.SysLog;
import com.edut.springboot.tarena.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Aspect 注解描述的类型为一个 AOP 应用中的切面类型，此类的构成：
 * 1)切入点 (在哪些方法执行时，我们要植入扩展功能):例如所有地铁站的入口
 * 2)通知 (要植入的扩展功能):例如安检
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

	@Autowired
	private SysLogService sysLogService ;
	
	//sysLogServiceImpl
	@Pointcut("bean(sysUserServiceImpl)")
	public void joinPoint() {} ; 
	
	@Around("joinPoint()")
	public Object around(ProceedingJoinPoint pj ) throws Throwable {
		try {
			long start = System.currentTimeMillis(); 
			Object result = pj.proceed();
			long  end = System.currentTimeMillis() ; 
			
			//写正常日志
			saveLog(pj , end-start) ; 
			
			return result ;
		} catch (Throwable e) {
			
			//写异常日志
			
			log.error(e.getMessage());
			throw e; 
		}
	}
	
	/*将用户行为信息写入到数据库 saveLog */
	private void saveLog(ProceedingJoinPoint pj, long time) throws NoSuchMethodException, SecurityException {
		
		// 1.获取行为日志（借助 连接点 对象 - JoinPoint）
		// 直接 - 对象(组合实现中，可能是接口) ==> 不可直接用注册对象获取方法
		MethodSignature signature =(MethodSignature) pj.getSignature(); // (MethodSignature) : 提供了参数的 字符串到类型的转换
		Object target = pj.getTarget(); 
		Class<?> clazz = target.getClass(); // 实现 - 类
		
		String ip =  "192.168.0.1";
		String username = "GBK1910";
		//1.3 获取目标方法实际参数
		String params = Arrays.toString(pj.getArgs());
		//1.4 获取操作名称(由此注解RequiredLog指定)
		//1.4.1 获取目标方法
		Method method = clazz.getDeclaredMethod(signature.getName() , signature.getParameterTypes());
		//Method method  = signature.getMethod() ; 
		String methodName =clazz.getName() +"."+ method.getName(); 
		
		/*
		 * 老师加了判断。。。。
		 */
		String operation ="operation" ; 
		RequiredLog annotation = method.getAnnotation(RequiredLog.class);
		if(annotation!=null) {
			operation = annotation.operation() ; 
		}
		
		//2. 封装为日志
		SysLog entity = new SysLog()
				.setIp(ip)
				.setUsername(username)  
				.setMethod(methodName)
				.setParams(params)
				.setOperation(operation)
				.setTime(time); 
		//3.写日志
		sysLogService.saveObject(entity);
	}
	
}
