package com.edut.springboot.tarena.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.type.Alias;
import org.springframework.core.annotation.AliasFor;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredLog {
	
	//@AliasFor(value = "value")
	String[] operation() default"operation" ; 
}
