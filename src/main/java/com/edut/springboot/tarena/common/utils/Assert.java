package com.edut.springboot.tarena.common.utils;

import com.edut.springboot.tarena.common.exception.ServiceException;

public abstract class Assert {
	/** 参数有效性校验 
	 * @param message 
	 * @param statement */
	public static void isArgumentValid(boolean statement, String message) {
		if(statement) {
			throw new IllegalArgumentException(message) ;
		}
	}
	/** 业务验证 */
	public static void isServiceValid(boolean statement, String message) {
		if(statement) {
			throw new ServiceException(message) ; 
		}
	}
	//.....
}
