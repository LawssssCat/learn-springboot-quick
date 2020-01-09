package com.edut.springboot.tarena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.service.SysLogService;

@RestController
@RequestMapping("/log")
public class SysLogController {
	
	@Autowired
	private SysLogService sysLogService ;
	
	//@ResponseBody
	@GetMapping("/doFindObjects")
	public JsonResult doFindObjects(
			 String name , 
			Integer pageCurrent) {
		return new JsonResult(sysLogService.findPageObject(name, pageCurrent));
	}
	
	//@ResponseBody
	@DeleteMapping("/doDeleteObjects")
	public JsonResult doDeleteObject( Long ... ids ) {
		Integer rows = sysLogService.deleteObject(ids) ; 
		return new JsonResult("delete ok. rows="+rows) ; 
	}
	
}
