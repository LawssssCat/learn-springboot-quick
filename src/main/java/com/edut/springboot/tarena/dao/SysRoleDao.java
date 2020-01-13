package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.edut.springboot.tarena.common.vo.SysRoleMenuVo;
import com.edut.springboot.tarena.pojo.SysRole;

@Mapper
public interface SysRoleDao {
	
	SysRoleMenuVo findObjectById(@Param("roleId") Integer id) ; 
	
	int insertObject(SysRole sysRole) ;

	/**
	 * 基于条件查询总记录数
	 * @param name 查询条件
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'username' in 'class java.lang.String'
	 */
	int getRowCount(@Param("name") String name);
	
	/**
	 * 基于条件分页查询角色信息
	 * @param name  查询条件
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的角色记录信息
	 * 数据库中每条角色信息封装到一个SysLog对象中
	 */
	List<SysRole> findObjects(
			@Param("name") String name , 
			@Param("startIndex") Integer startIndex , 
			@Param("pageSize") Integer pageSize) ;
	
	
	@Delete("delete from sys_roles where id = #{id}")
	int deleteObject(@Param("id") Integer id);

	@Update("update sys_roles set name=#{name} , note=#{note} where id = #{id}")
	int updateObject(
			@Param("id") Integer id, 
			@Param("name") String name, 
			@Param("note") String note);
}
