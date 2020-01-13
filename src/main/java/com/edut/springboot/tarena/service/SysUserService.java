package com.edut.springboot.tarena.service;


import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.common.vo.SysUserDeptVo;

public interface SysUserService {
	PageObject<SysUserDeptVo> findPageObjects(Integer pageCurrent, String username);
}
