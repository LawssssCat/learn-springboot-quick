package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.config.PaginationProperties;
import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.common.vo.SysUserDeptVo;
import com.edut.springboot.tarena.dao.SysUserDao;
import com.edut.springboot.tarena.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao ;
	
	@Autowired
	private PaginationProperties paginationProperties ; 

	@Override
	public PageObject<SysUserDeptVo> findPageObjects(Integer pageCurrent, String username) {
		/**
		 * 参数校验
		 */
		//Assert.isEmpty(username, "用户名不能为空！ %_%");
		Assert.isErrorInteger(pageCurrent, "页码异常！ $_$");
		Integer pageSize = paginationProperties.getPageSize();
		Assert.isErrorInteger(pageSize , "页面参数") ;
		Integer startIndex = paginationProperties.getStartIndex(pageCurrent); 
		/**
		 * 执行查询 $ 校验
		 */
		Integer rowCount = sysUserDao.getRowCount(username) ;
		Assert.isArgumentValid(rowCount==0, "没有数据源 ~_~");
		
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username , pageSize , startIndex )  ;
		Assert.isListEmpty(records, "数据可能不存在了！");
		
		return new PageObject<SysUserDeptVo>(rowCount, records, pageSize, pageCurrent);
	} 
	
}
