package com.edut.springboot.tarena.common.vo;


import com.edut.springboot.tarena.pojo.SysDept;
import com.edut.springboot.tarena.pojo.SysUser;

import lombok.Data;

@Data
public class SysUserDeptVo extends SysUser  {
	
	private static final long serialVersionUID = 3236709082715096978L;
	private SysDept sysDept; //private Integer deptId;

}
