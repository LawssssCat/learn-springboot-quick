package com.edut.springboot.tarena.common.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	private void saveLog(ProceedingJoinPoint pj, long time) {
		//AutoWired
		//SysLogService ... 
		/*将用户行为信息写入到数据库 saveLog */
		//private void saveLog(JoinPoint , time) ... 
		/*
		 * 1.获取行为日志（借助 连接点 对象 - JoinPoint）
		 * 2.封装行为日志
		 * 	id
		 * 	username
		 * 	operation
		 * 	method 、、 目标方法：类全名+方法名
		 * 	params 、、 目标方法实际参数
		 * 	time(操作时间)
		 * 	createTime
		 * 
		 * 3.写日志
		 */
		Signature signature = pj.getSignature();
		Class<?> clazz = pj.getTarget().getClass();
		
		String ip =  "192.168.0.1";
		String username = "GBK1910";
		String method = clazz.getName() + "."+signature.getName() ;//类全名+.+方法名
		String params = Arrays.toString(pj.getArgs());
		String operation = "operation";
		
		SysLog entity = new SysLog()
				.setIp(ip)
				.setUsername(username)  
				.setMethod(method)
				.setParams(params)
				.setOperation(operation)
				.setTime(time); 
		sysLogService.saveObject(entity);
	}
	
}
