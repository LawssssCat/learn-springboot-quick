package com.edut.springboot.tarena.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {

	/**
	 * 基于菜单id删除角色和菜单关系数据
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(@Param("id") Integer menuId);
}
