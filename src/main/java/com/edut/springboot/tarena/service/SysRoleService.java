package com.edut.springboot.tarena.service;

import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.pojo.SysRole;

public interface SysRoleService {

	PageObject<SysRole> findPageObjects(String name , Integer pageCurrent) ; 
}
