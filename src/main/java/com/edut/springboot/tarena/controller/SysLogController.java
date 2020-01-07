package com.edut.springboot.tarena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.service.SysLogService;

@Controller
@RequestMapping("/log")
public class SysLogController {
	
	@Autowired
	private SysLogService sysLogService ;
	
	@ResponseBody
	@RequestMapping("/doFindObjects")
	public JsonResult doFindObjects(String name , Integer pageCurrent) {
		return new JsonResult(sysLogService.findPageObject(name, pageCurrent));
	}
}
