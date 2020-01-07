package com.edut.springboot.tarena.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SysLog implements Serializable {

	/**
	 * 序列化的版本id，没有此id，在序列化时候会基于类的结构自动生成id，
	 * 但是一旦类的结构发生变化，假如此id没有显式声明，可能会反序列化失败（抛出异常）。
	 */
	private static final long serialVersionUID = -1256888357038578412L;
	
	private Long id ; 
	private String username ; 
	private String operation ; 
	private String method ;
	private String params ; 
	private Long time ; 
	private String ip; 
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime ; 
	
}
