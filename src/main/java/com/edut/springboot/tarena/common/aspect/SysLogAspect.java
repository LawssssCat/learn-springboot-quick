package com.edut.springboot.tarena.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

	//sysLogServiceImpl
	@Pointcut("bean(sysLogService)")
	public void joinPoint() {} ; 
	
	@Around("joinPoint()")
	public Object around(ProceedingJoinPoint pj ) throws Throwable {
		try {
			log.info("start:{}" , System.currentTimeMillis());
			Object result = pj.proceed();
			log.info("after:{}" , System.currentTimeMillis());
			return result ;
		} catch (Throwable e) {
			log.error(e.getMessage());
			throw e; 
		}
	}
}
