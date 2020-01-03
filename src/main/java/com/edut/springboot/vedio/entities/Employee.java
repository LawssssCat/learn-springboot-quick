package com.edut.springboot.vedio.entities;

import java.util.Date;

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
	private Date birth ; 
	

	public Employee() {
	}

}
