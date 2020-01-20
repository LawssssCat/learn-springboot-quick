package com.edut.springboot.tarena.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.annotation.ClearCache;
import com.edut.springboot.tarena.common.annotation.RequiredCache;
import com.edut.springboot.tarena.common.annotation.RequiredLog;
import com.edut.springboot.tarena.common.exception.ServiceException;
import com.edut.springboot.tarena.common.utils.Assert;import com.edut.springboot.tarena.common.utils.ShiroUtils;
import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.common.vo.Node;
import com.edut.springboot.tarena.dao.SysMenuDao;
import com.edut.springboot.tarena.dao.SysRoleMenuDao;
import com.edut.springboot.tarena.pojo.SysMenu;
import com.edut.springboot.tarena.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao ;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao ; 
	
	@Cacheable(cacheNames = "menuCache")
	@RequiredLog(operation = "查询")
	//@Cacheable(value = "menuCache")
	//@RequiredCache // 自己的cache
	@Override
	public JsonResult findObjects() {
		List<Map<String, Object>> data = sysMenuDao.findObjects();
		Assert.isServiceValid(data == null , "没有数据！");
		return new JsonResult(data) ;   
	}
	
	@CacheEvict(	beforeInvocation = false  , 
					value = "menuCache" , 
					allEntries = true)
	@RequiredLog(operation = "删除")
	@Override
	public int deleteObject(Integer id ) {
		//1. 参数校验 null , <1
		Assert.isArgumentValid(id==null || id<1, "id错误！~");
		//2. 统计当前菜单对应的子菜单个数并校验 childCount>0
		int childCount = sysMenuDao.getChildCount(id);
		Assert.isServiceValid(childCount>0, "请先删除子元素");
		//3. 删除菜单对应的关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id); 
		//4. 删除菜单自身信息并校验 rows==0
		int rows = sysMenuDao.deleteObjcet(id) ; 
		Assert.isServiceValid(rows==0, "记录可能已经不存在了 ！");
		return rows ;  
	}


	@RequiredCache
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes() ;
	}


	@RequiredLog(operation =  "添加")
	//@CacheEvict(value = "menuCache" ,allEntries = true ,beforeInvocation = false)
	//@ClearCache
	@Override
	public int saveObject(SysMenu entity) {
		Assert.isArgumentValid(entity==null , "数据不能为空!!!");
		Assert.isEmpty(entity.getName(), "用户名不能为空!!!");
		int rows = -1  ; 
		try {
			String username = ShiroUtils.getUsername() ; 
			entity.setCreatedUser(username);
			rows = sysMenuDao.saveObject(entity) ; 
		}catch (Exception e) {
			e.getStackTrace() ; 
			throw new ServiceException("服务器异常....") ; 
		}
		
		return rows ;
	}

	@RequiredLog(operation = "修改")
	public int updateObject(SysMenu entity) {
		Assert.isArgumentValid(entity==null , "数据不能为空!!!");
		Assert.isEmpty(entity.getName(), "用户名不能为空!!!");
		try {
			String username = ShiroUtils.getUsername() ; 
			entity.setModifiedUser(username);
			entity.setModifiedTime(new Date(new java.util.Date().getTime()));
			return sysMenuDao.updateObject(entity) ;
		}catch (Exception e) {
			e.getStackTrace() ; 
			throw new ServiceException("服务器异常....") ; 
		}
	}



}
