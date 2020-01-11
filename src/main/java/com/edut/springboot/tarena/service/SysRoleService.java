package com.edut.springboot.tarena.service;

import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.pojo.SysRole;

public interface SysRoleService {

	/**
	 * 保存角色信息以及角色菜单关系数据
	 */
	int saveObject(Sys) {
		//1. 参数校验 ==null name=isEmpty
		//2. 保存角色自身信息
		//3. 保存角色菜单关系数据
		//4. 返回结果
	}
	
	
	PageObject<SysRole> findPageObjects(String name , Integer pageCurrent) ; 
}
