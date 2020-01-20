package com.edut.springboot.tarena.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * POJO:(普通的java对象)
 * 1)PO(持久化对象):与表中字段有一一映射关系
 * 2)VO(值对象 - Value object) 用于封装业务数据
 * 		(可能在层与层之间传递)
 * 		(一般和表中字段没有一一映射关系)
 * 3)DAO
 * 4)BO 业务层...
 * ...
 */
@Data
@Accessors(chain = true) 
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
