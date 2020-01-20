package com.edut.springboot.tarena.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
@Aspect
public class SysExceptionAspect {

	
	/**
	 * 异常通知：
	 * jp 连接点对象(指向了正在执行的 目标方法)
	 * e 此变量 用于接收执行目标方法出现的异常
	 */
	@AfterThrowing(pointcut = "bean(*ServiceImpl)" ,throwing = "e")
	public void doHandleException(JoinPoint jp , Throwable e) {
		Signature signature = jp.getSignature();
		log.error("{} exception msg is {}" , signature.getName() ,  e.getMessage());
	}
}
