package com.edut.springboot.tarena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.pojo.SysMenu;
import com.edut.springboot.tarena.service.impl.SysMenuServiceImpl;

@RequestMapping("/menu")
@RestController
public class SysMenuController {

	@Autowired
	private SysMenuServiceImpl sysMenuService ;
	
	@RequestMapping("/doFindObjects")
	public JsonResult doFindObjects() {
		return sysMenuService.findObjects() ; 
	}
	
	@RequestMapping("/doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		return new JsonResult("delete ok! rows="+sysMenuService.deleteObject(id)) ; 
	}
	
	@RequestMapping("/doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(sysMenuService.findZtreeMenuNodes());
	}
	
	@RequestMapping("/doSaveObject")
	public JsonResult doSaveObject(SysMenu entity) {
		int rows = sysMenuService.saveObject(entity) ; 
		return new JsonResult("save ok ! roows="+rows) ; 
	}
	
}
