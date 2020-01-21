package com.edut.springboot.tarena.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edut.springboot.tarena.common.annotation.ClearCache;
import com.edut.springboot.tarena.common.annotation.RequiredCache;
import com.edut.springboot.tarena.common.annotation.RequiredLog;
import com.edut.springboot.tarena.common.exception.ServiceException;
import com.edut.springboot.tarena.common.utils.Assert;import com.edut.springboot.tarena.common.utils.ShiroUtils;
import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.common.vo.Node;
import com.edut.springboot.tarena.common.vo.SysUserMenuVo;
import com.edut.springboot.tarena.dao.SysMenuDao;
import com.edut.springboot.tarena.dao.SysRoleMenuDao;
import com.edut.springboot.tarena.dao.SysUserRoleDao;
import com.edut.springboot.tarena.pojo.SysMenu;
import com.edut.springboot.tarena.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao ;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao ; 
	
	@RequiresPermissions("sys:menu:view")
	@Cacheable(cacheNames = "menus")
	@RequiredLog(operation = "查询")
	@Override
	public JsonResult findObjects() {
		List<Map<String, Object>> data = sysMenuDao.findObjects();
		Assert.isServiceValid(data == null , "没有数据！");
		return new JsonResult(data) ;   
	}
	
	
	@RequiresPermissions("sys:menu:delete")
	@Transactional
	@Caching(evict = {
			@CacheEvict(allEntries = true , cacheNames = {"menus" , "ZtreeMenu"}) , 
			@CacheEvict(key = "#id" , cacheNames = {"userManus"}) 
	})
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


	@RequiresPermissions("sys:menu:view")
	@Cacheable("ZtreeMenu")
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes() ;
	}


	@RequiresPermissions("sys:menu:add")
	@Transactional
	@Caching(evict = {
			@CacheEvict(allEntries = true , cacheNames = {"menus" , "ZtreeMenu"}) ,  //保存时候没有id
	})
	@RequiredLog(operation =  "添加")
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

	@RequiresPermissions("sys:menu:update")
	@Transactional
	@Caching(evict = {
			@CacheEvict(allEntries = true , cacheNames = {"menus" , "ZtreeMenu"}) , 
			@CacheEvict(key = "#entity.id" , cacheNames = {"userManus"}) 
	})
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


	//@Cacheable(key = "#userId" , cacheNames = "userManus")
	@Override
	public List<SysUserMenuVo> findUserMenus(Integer userId) {
		
		Assert.isArgumentValid(userId==null||userId<1, "用户异常");
		//检验 userId
		
		Integer[] array = {} ; 
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
		List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array)); 
		List<SysUserMenuVo> list = sysMenuDao.findUserMenus(menuIds.toArray(array)); 
		
		Assert.isServiceValid(list==null||list.size()==0, "服务异常");
		
		return list;
	}



}
