package com.edut.springboot.tarena.controller;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.pojo.SysUser;
import com.edut.springboot.tarena.service.SysUserService;

@RequestMapping("/user")
@RestController
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService ; 

	@RequestMapping("/doFindPageObjects")
	public JsonResult doFindPageObjects(Integer pageCurrent ,String username) {
		return new JsonResult(sysUserService.findPageObjects(pageCurrent , username));
	}

	@RequestMapping("/doValidById")
	public JsonResult doValidById(Integer id , Integer valid) {
		return new JsonResult("update ok ! rows="+sysUserService.validById(id, valid)) ;
	}
	
	@RequestMapping("/doSaveObject")
	public JsonResult doSaveObject(SysUser sysUser , Integer[] roleIds) {
		int rows = sysUserService.saveObject(sysUser , roleIds);
		return new JsonResult("save ok ! rows="+rows) ;
	}

	///user/doFindObjectById?id=20
	@RequestMapping("/doFindObjectById")
	public JsonResult doFindObjectById(Integer  id) {
		return new JsonResult(sysUserService.findObjectById(id));
	}
}
