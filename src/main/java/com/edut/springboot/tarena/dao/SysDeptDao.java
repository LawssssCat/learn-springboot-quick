package com.edut.springboot.tarena.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edut.springboot.tarena.pojo.SysDept;

@Mapper
public interface SysDeptDao {

	@Select("select * from sys_depts where id = #{id}")
	SysDept findById(@Param("id") Integer id) ; 
}
