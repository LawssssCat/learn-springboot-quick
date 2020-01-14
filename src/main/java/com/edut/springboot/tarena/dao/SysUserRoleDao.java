package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserRoleDao {

	int deleteObjectsByRoleId(@Param("id") Integer roleId ) ;

	
	int insertObjects(
			@Param("userId") Integer id, 
			@Param("roleIds") Integer ... roleIds);


	@Select("select role_id from sys_user_roles where user_id = #{userId}")
	List<Integer> findRoleIdsByUserId(@Param("userId") Integer userId);
}
