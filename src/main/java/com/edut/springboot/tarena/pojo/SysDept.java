package com.edut.springboot.tarena.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysDept implements Serializable{
	private static final long serialVersionUID = 7635027582996533917L;
	private Integer id;
	private String name;
	private Integer parentId ; 
	private Integer sort; 
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
