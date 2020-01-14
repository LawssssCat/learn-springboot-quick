package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.edut.springboot.tarena.common.vo.SysUserDeptVo;
import com.edut.springboot.tarena.pojo.SysUser;

@Mapper
public interface SysUserDao {
	/**
	 * 禁用/启动用户对象
	 * valid 状态值
	 * modifiedUser 修改用户
	 * update sys_users set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}
	 */
	@Update("update sys_users set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
	int validById(
			@Param("id") Integer id , 
			@Param("valid") Integer valid ,
			@Param("modifiedUser") String modifiedUser) ;
	

	int getRowCount(@Param("username") String username) ;

	List<SysUserDeptVo> findPageObjects(
			@Param("username") String username, 
			@Param("pageSize") Integer pageSize, 
			@Param("startIndex") Integer startIndex);


	int insertObject(SysUser sysUser);

	SysUserDeptVo findObjectById(@Param("id") Integer id);
}
