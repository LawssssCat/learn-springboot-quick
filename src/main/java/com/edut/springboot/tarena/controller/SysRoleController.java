package com.edut.springboot.tarena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.service.SysRoleService;

@RequestMapping("/role")
@RestController
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService ; 
	
	sadfsa
	
	@RequestMapping("/doFindPageObjects")
	public JsonResult dofindPageObject(String name , Integer pageCurrent) {
		return new JsonResult(sysRoleService.findPageObjects(name, pageCurrent));
	}
	

}
