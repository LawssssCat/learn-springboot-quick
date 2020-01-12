package com.edut.springboot.tarena.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {

	/**
	 * 写入角色 目录 关系 
	 * @param menuIds 
	 * @param id 
	 */
	int insertObjects(
			@Param("roleId") int id, 
			@Param("menuIds") Integer ... menuIds) ; 
	
	/**
	 * 基于菜单id删除角色和菜单关系数据
	 * @param menuId
	 * @returnW
	 */
	@Delete("delete from sys_role_menus where  menu_id = #{id}")
	int deleteObjectsByMenuId(@Param("id") Integer menuId);
	
	/** 基于角色id... */
	@Delete("delete from sys_role_menus where role_id = #{id}")
	int deleteObjectsByRoleId(@Param("id") Integer roleId);
}
