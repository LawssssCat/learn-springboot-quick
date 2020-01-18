package com.edut.springboot.tarena.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = 5536877168219470352L;
	private Integer id;
	private String username;
	private String password;//md5
	private String salt; //颜值 
	private String email;
	private String mobile;
	private Integer valid=1; //状态 1=OK 0=禁用
	private Integer deptId ; 
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
