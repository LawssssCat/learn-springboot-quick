package com.edut.springboot.vedio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edut.springboot.vedio.dao.EmployeeDao;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao ; 

	@GetMapping("/emps")
	public String list(Model model ) {
		
		
		
		return "/emp/list" ; 
	}
	
}

