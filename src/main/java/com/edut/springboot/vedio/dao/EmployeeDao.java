package com.edut.springboot.vedio.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edut.springboot.vedio.entities.Department;
import com.edut.springboot.vedio.entities.Employee;

@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null ; 
	
	@Autowired
	private DepartmentDao departmentDao ; 
	
	public EmployeeDao() {
	}
	
	static {
		employees = new HashMap<Integer, Employee>() ;
		
		employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101 , "DD-AA"), new Date("1877/12/1") ));
	}

}
