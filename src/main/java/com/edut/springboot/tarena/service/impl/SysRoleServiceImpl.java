package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.config.PaginationProperties;
import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.dao.SysRoleDao;
import com.edut.springboot.tarena.dao.SysRoleMenuDao;
import com.edut.springboot.tarena.dao.SysUserRoleDao;
import com.edut.springboot.tarena.pojo.SysRole;
import com.edut.springboot.tarena.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private PaginationProperties pagiantionProperties; 
	
	@Autowired
	private SysRoleDao sysRoleDao ;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao ; 

	@Override
	public PageObject<SysRole> findPageObjects(String name , Integer pageCurrent){
		//1. 参数校验 null <1
		Assert.isArgumentValid(pageCurrent==null || pageCurrent<1, "页码值错误！");
		//2. 查询[总]记录数并校验  ==0
		int rowCount = sysRoleDao.getRowCount(name); // 分页 - 一定要 - 总页数
		Assert.isServiceValid(rowCount == 0, "没有数据~~~");
		//3. 查询当前页角色信息记录
		Integer pageSize = pagiantionProperties.getPageSize() ;
		Integer startIndex = pagiantionProperties.getStartIndex( pageCurrent );
		List<SysRole> records = sysRoleDao.findObjects( name , startIndex , pageSize );
		//4. 封装查询结果
		return new PageObject<SysRole>(rowCount, records, pageSize, pageCurrent); 
	}

	@Override
	public int deleteObject(Integer id) {
		/**
		 * 0. 参数校验
		 */
		Assert.isArgumentValid(id==null || id<1, "请先选择！");
		/**
		 * 1. 删除关系数据
		 */
		//删除 Role - Menu
		sysRoleMenuDao.deleteObjectsByRoleId(id) ; 
		//删除 Role - User
		sysUserRoleDao.deleteObjectsByRoleId(id) ;
		/**
		 * 2. 删除角色自身数据
		 */
		//删除 Role
		int rows = sysRoleDao.deleteObject(id) ;
		/**
		 * 结果校验
		 */
		Assert.isServiceValid(rows==0,"记录已经不存在！");
		return rows ;
	}

	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		/**
		 * 参数校验
		 */
		Assert.isArgumentValid(entity==null, "请填写数据");
		Assert.isEmpty(entity.getName(), "名称不能为空！");
		Assert.isArgumentValid(menuIds==null || menuIds.length==0, "必须为角色赋予权限~~");
		/**
		 * 保存
		 */
		int rows = sysRoleDao.insertObject(entity) ;
		int id = entity.getId();
		sysRoleMenuDao.insertObjects(id , menuIds) ;
		/**
		 * 结果校验
		 */
		Assert.isServiceValid(rows==0, "服务异常...");
		return rows;
	}

}
