package com.edut.springboot.tarena.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

	int deleteObjectsByRoleId(@Param("id") Integer roleId ) ;
}
