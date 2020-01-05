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
		
		employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101 , "DD-AA"), new Date("1977/12/1") ));
		employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 0, new Department(102 , "DD-BB"), new Date("1977/11/22") ));
		employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 1, new Department(103 , "DD-CC"), new Date("2000/01/31") ));
		employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104 , "DD-DD"), new Date("1988/3/2") ));
		employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105 , "DD-EE"), new Date("1977/12/1") ));
		employees.put(1006, new Employee(1006, "E-FF", "ff@163.com", 1, new Department(106 , "DD-FF"), new Date("1955/1/11") ));
	}
	
	public void save(Employee employee) {
		if(employee!=null) {
			
			Department department = departmentDao.getDepartment(employee.getDepartment().getId());
			employee.setDepartment(department );
			employees.put( employee.getId(),employee) ;
		}
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
