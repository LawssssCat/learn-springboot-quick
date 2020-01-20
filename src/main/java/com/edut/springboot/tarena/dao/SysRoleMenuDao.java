package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
	
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds") Integer ... roleIds) ;
	
	List<Integer> findMenuIdsByRoleId(
			@Param("roleId") Integer roleId) ;

	/**写入角色 目录 关系 */
	int insertObjects(
			@Param("roleId") int id, 
			@Param("menuIds") Integer ... menuIds) ; 
	
	/**基于菜单id删除角色和菜单关系数据*/
	@Delete("delete from sys_role_menus where  menu_id = #{id}")
	int deleteObjectsByMenuId(
			@Param("id") Integer menuId);
	
	/** 基于角色id... */
	@Delete("delete from sys_role_menus where role_id = #{id}")
	int deleteObjectsByRoleId(
			@Param("id") Integer roleId);
	
}
