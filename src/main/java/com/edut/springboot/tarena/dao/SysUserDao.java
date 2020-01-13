package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edut.springboot.tarena.common.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {

	int getRowCount(@Param("username") String username) ;

	List<SysUserDeptVo> findPageObjects(
			@Param("username") String username, 
			@Param("pageSize") Integer pageSize, 
			@Param("startIndex") Integer startIndex);
}
