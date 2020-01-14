package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edut.springboot.tarena.common.vo.Node;
import com.edut.springboot.tarena.pojo.SysDept;

@Mapper
public interface SysDeptDao {

	@Select("select * from sys_depts where id = #{id}")
	SysDept findById(@Param("id") Integer id) ;

	@Select("select id , name , parentId from sys_depts")
	List<Node> findZTreeNodes(); 
}
