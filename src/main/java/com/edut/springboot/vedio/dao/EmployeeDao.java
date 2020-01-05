package com.edut.springboot.vedio.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edut.springboot.vedio.entities.Department;
import com.edut.springboot.vedio.entities.Employee;

import io.micrometer.shaded.org.pcollections.PMap;

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
	
	public void save(Employee employee) {
		employees.put(employee.getId(),employee) ; 
	}
	
	
	//查询所有员工
	public Collection<Employee> getAll() {
		return this.employees.values() ; 
	}
	public Employee get(Integer  id) {
		return employees.get(id);
	}
	public void delete(Integer id ) {
		employees.remove(id);
	}
	

}
