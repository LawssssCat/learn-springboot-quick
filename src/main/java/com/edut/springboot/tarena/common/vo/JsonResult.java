package com.edut.springboot.tarena.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基于此对象封装控制层要响应到客户端的数据 
 */
@Data
@NoArgsConstructor
public class JsonResult implements Serializable {

	private static final long serialVersionUID = -951757736934509555L;
	
	/**
	 * 1 : OK
	 * 0 : Failed
	 */
	private int state = 1  ;
	/**状态信息 */
	private String message ; //
	/** 借助此属性，封装控制层从业务层获取的数据 */
	private Object data ;
	
	public JsonResult(String message) {
		this.message = message ; 
	}
	public JsonResult(Object data) {
		this.data = data ; 
	}
	public JsonResult( Throwable e) { 
		this.state = 0 ; 
		this.message = e.getMessage() ; 
	}
}
