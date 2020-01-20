package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserRoleDao {


	
	int insertObjects(
			@Param("userId") Integer userId, 
			@Param("roleIds") Integer ... roleIds);


	@Select("select role_id from sys_user_roles where user_id = #{userId}")
	List<Integer> findRoleIdsByUserId(
			@Param("userId") Integer userId);

	@Delete("delete from sys_user_roles where  role_id = #{roleId}")
	int deleteObjectsByRoleId(
			@Param("roleId") Integer roleId ) ;
	
	@Delete("delete from sys_user_roles where  user_id = #{userId}")
	int deleteObjectsByUserId(
			@Param("userId") Integer userId);
}
