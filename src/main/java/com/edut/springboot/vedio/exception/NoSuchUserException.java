package com.edut.springboot.vedio.exception;

public class NoSuchUserException extends RuntimeException {

	public NoSuchUserException() {
		super("用户不存在！");
	}
	
}
