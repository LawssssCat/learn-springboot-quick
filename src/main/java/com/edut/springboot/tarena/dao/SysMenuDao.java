package com.edut.springboot.tarena.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edut.springboot.tarena.common.vo.Node;
import com.edut.springboot.tarena.pojo.SysMenu;

@Mapper
public interface SysMenuDao {
	
	int saveObject(SysMenu entity) ;
	
	@Select("select id, name ,  parentId from sys_menus")
	List<Node> findZtreeMenuNodes() ;
	
	/**
	 * 基于菜单id统计其子元素的个数
	 * getChildCount
	 */
	int getChildCount(@Param("id") Integer menuId) ;
	
	/**
	 * 基于id删除当前菜单对象
	 * deleteObjcet
	 */
	int deleteObjcet(@Param("id") Integer menuId) ;

	/**
	 * 查询所有菜单以及菜单对应的上级菜单id和名称
	 * @return 所有菜单对应的一个集合(一行记录映射为一个map,map中key为字段名)
	 * 
	 * ------------------------------------
	 * 
	 * 为什么使用map?
	 * 优势:
	 * 1. map本身是一个容器，可以一key/value的形式存储数据
	 * 2. 通过map存储数据，可以省略我们自己对pojo对象的定义(《------- 这也是最大的优势)
	 * 
	 * 劣势:
	 * 1. 值的类型不可控
	 * 2. 并且可读性差
	 */
	List<Map<String, Object>> findObjects() ;
}
