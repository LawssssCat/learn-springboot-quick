package com.edut.springboot.vedio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.edut.springboot.vedio.dao.DepartmentDao;
import com.edut.springboot.vedio.dao.EmployeeDao;
import com.edut.springboot.vedio.entities.Department;
import com.edut.springboot.vedio.entities.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {
	
	//只需要加上下面这段即可（接收指定格式日期），注意不能忘记注解
//	@InitBinder
//	public void initBinder(WebDataBinder binder, WebRequest request) {
//		//转换日期
//		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//		// CustomDateEditor为自定义日期编辑器
//	}
	
	
	@Autowired
	private EmployeeDao employeeDao ;
	
	@Autowired
	private DepartmentDao departmentDao ; 

	@GetMapping("/emps")
	public String list(Model model ) {
		
		Collection<Employee> emps = employeeDao.getAll();
		model.addAttribute("emps", emps) ; 
		
		return "/emp/list" ; 
	}
	
	@GetMapping("/emp")
	public String toAddPage(Model model) {
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("departments" , departments) ; 
		return "emp/add" ; 
	}
	
	/**
	 * 员工添加
	 * SpringMVC 自动将请求参数和入参对象的属性进行一一绑定
	 * 要求：
	 * 		请求参数的名字和javaBean入参的对象的属性名一样
	 * @throws JsonProcessingException 
	 */
	@PostMapping("/emp")
	public String addEmp(Employee employee) throws JsonProcessingException {
		
		System.out.println(employee.getBirth());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(employee);
		System.out.println(json);
		
		employeeDao.save(employee);
		
		//来到员工列表页面
		//redirect：表示重定向地址
		//forward：表示转发到地址
		return "redirect:/emps" ;
	}
	
	


	
}

