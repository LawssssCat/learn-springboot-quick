package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.edut.springboot.tarena.pojo.SysLog;

/**
 * 定义日志数据层接口：SysLogDao
 * 在此接口中定义 
 */
@Mapper
public interface SysLogDao {
	/**
	 * 基于ids的值，删除
	 */
	int deleteObjects(@Param("ids") Long ...ids) ; 
	
	/**
	 * 基于条件查询总记录数
	 * @param username 查询条件(例如查询哪个用户的日志信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'username' in 'class java.lang.String'
	 */
	int getRowCount(@Param("username") String username);
	
	/**
	 * 基于条件分页查询日志信息
	 * @param username  查询条件(例如查询哪个用户的日志信息)
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的日志记录信息
	 * 数据库中每条日志信息封装到一个SysLog对象中
	 * 
	 * pageHelper底层：sqlSession.selctList(statement , args)
	 * 拦截器:intercepter --> select * from sys_logs order createTime 
	 * 修改
	 * 1) 添加sql:select count(0) from sys_logs
	 * 2) 修改sql:select * from sys_logs order by createTime limit ? , ? 
	 * 
	 */
	List<SysLog> findPageObjects(@Param("username") String username ) ; 
}
