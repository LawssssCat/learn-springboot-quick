package com.edut.springboot.tarena.common.aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class SysCacheAspect {

	private Map<String, Object> map = new ConcurrentHashMap<>() ; 

	@Pointcut("@annotation(com.edut.springboot.tarena.common.annotation.RequiredCache)")
	public void doCache() {} ;
	
	
	@Around("doCache()")
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		
		Signature signature = jp.getSignature();
		
		String key =  signature.toLongString();
		
		log.info("Get data from cache:"+key );
		Object obj = map.get(key);
		if(obj!=null) 
			return  obj ;
		
		try { // 没有数据
			obj = jp.proceed() ;
			map.put(key, obj);
			log.info("Put data from cache:"+key );
			return obj ; 
		}catch (Exception e) {
			throw e ; 
		}
	}
}
