package com.edut.springboot.tarena.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 菜单 节点信息
 */
@Data
public class Node implements Serializable {
	private static final long serialVersionUID = -54176750743566921L;
	private Integer id ; 
	private String name ; 
	private Integer parentId ; 
}
