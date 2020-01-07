package com.edut.springboot.vedio.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Employee {
	private Integer id ; 
	private String lastName ; 
	private String email ; 
	
	//1 male , 0 female
	private Integer gender ;
	private Department department ; 
	
	//@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")//写入 - 格式
	@JsonFormat(pattern = "yyyy-MM-dd")//读出 - 格式
	private Date birth ; 
	

	public Employee() {
	}

}
