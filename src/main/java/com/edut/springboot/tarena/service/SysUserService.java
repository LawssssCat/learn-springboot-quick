package com.edut.springboot.tarena.service;


import java.util.Map;

import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.common.vo.SysUserDeptVo;
import com.edut.springboot.tarena.pojo.SysUser;

public interface SysUserService {
	PageObject<SysUserDeptVo> findPageObjects(Integer pageCurrent, String username);
	/**
	 * 状态
	 */
	int validById(Integer id , Integer valid) ;
	int saveObject(SysUser sysUser, Integer[] roleIds);
	int updateObject(SysUser sysUser, Integer[] roleIds); 
	Map<String, Object> findObjectById(Integer id);
	void isExist(String columnName , String columnValue)  ;
	void doLogin(String username, String password); 
}
