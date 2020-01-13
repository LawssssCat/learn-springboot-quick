package com.edut.springboot.tarena.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.common.vo.SysRoleMenuVo;
import com.edut.springboot.tarena.pojo.SysRole;
import com.edut.springboot.tarena.service.SysRoleService;

@RequestMapping("/role")
@RestController
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService ; 
	
	
	@RequestMapping("/doFindPageObjects")
	public JsonResult dofindPageObject(
			 String name , 
			@RequestParam("pageCurrent") Integer pageCurrent) {
		return new JsonResult(sysRoleService.findPageObjects(name, pageCurrent));
	}
	
	@RequestMapping("/doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		return new JsonResult("delete ok! rows="+sysRoleService.deleteObject(id)) ; 
	}
	
	@RequestMapping("/doSaveObject")
	public JsonResult doSaveObject(SysRole entity , @Param("menuIds") Integer[] menuIds) {
		return new JsonResult("save ok! rows="+sysRoleService.saveObject(entity , menuIds));
	}

	@RequestMapping("/doFindObjectById")
	public JsonResult doFindObjectById(@RequestParam("id") Integer id) {
		return new JsonResult(sysRoleService.findObjectById(id)) ; 
	}

	@RequestMapping("/doUpdateObject")
	public JsonResult doUpdateObject(SysRoleMenuVo entity) {
		return  new JsonResult("update ok! rows="+sysRoleService.updateObject(entity));
	}
	
}
