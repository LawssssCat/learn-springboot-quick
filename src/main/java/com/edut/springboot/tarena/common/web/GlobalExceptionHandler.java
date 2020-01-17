package com.edut.springboot.tarena.common.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edut.springboot.tarena.common.vo.JsonResult;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	//@ResponseBody
	public JsonResult runtimeExceptionHandle(Exception e) {
		return new JsonResult(e);
	}
}
