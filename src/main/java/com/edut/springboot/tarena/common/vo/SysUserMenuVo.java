package com.edut.springboot.tarena.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SysUserMenuVo implements Serializable {
	private static final long serialVersionUID = 6026478013796443420L;

	private Integer id ; 
	private String name ; 
	private String url ; 
	private List<SysUserMenuVo> childs ; 

}
