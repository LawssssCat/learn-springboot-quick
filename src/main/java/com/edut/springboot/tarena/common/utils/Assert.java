package com.edut.springboot.tarena.common.utils;

import java.util.List;

import com.edut.springboot.tarena.common.exception.ServiceException;
import com.edut.springboot.tarena.pojo.SysMenu;
import com.edut.springboot.tarena.pojo.SysRole;

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
	/** 字符串空 */
	public static void isEmpty(String data, String message) {
		if(data==null || "".equals(data)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static <T> void isListEmpty(List<T> records, String message) {
		if(records==null || records.size()==0) {
			throw new IllegalArgumentException(message) ; 
		}
	}
	public static void isErrorInteger(Integer i, String message) {
		if(i==null || i<1) {
			throw new IllegalArgumentException(message) ; 
		}
	}
}
