package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.config.PaginationProperties;
import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.dao.SysRoleDao;
import com.edut.springboot.tarena.pojo.SysRole;
import com.edut.springboot.tarena.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private PaginationProperties pagiantionProperties; 
	
	@Autowired
	private SysRoleDao sysRoleDao ; 

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

}
