package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.exception.ServiceException;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.dao.SysLogDao;
import com.edut.springboot.tarena.pojo.SysLog;
import com.edut.springboot.tarena.service.SysLogService;

@Service
public class SyslogServiceImpl implements  SysLogService {
	
	@Autowired
	private SysLogDao sysLogDao ;

	/**
【】我的垃圾代码
	@Override
	public PageObject<SysLog> findPageObject(String name, Integer pageCurrent) {
		Integer pageSize = 3;
		List<SysLog> objs = sysLogDao.findPageObjects(name, (pageCurrent-1)*pageSize , pageSize);
		return new PageObject<SysLog>(objs.size(), objs, pageSize, pageCurrent) ;
	}
【】齐雷的神仙代码。。
	*/
	@Override
	public PageObject<SysLog> findPageObject(String name, Integer pageCurrent) {
		//1.参数校验
		if(pageCurrent == null || pageCurrent<1 ) 
			throw new IllegalArgumentException("页码值不正确, pageCurrent="+pageCurrent);
		
		//2.基于用户名检查总记录数并校验
		int rowCount = sysLogDao.getRowCount(name);
		if(rowCount == 0) 
			throw new ServiceException() ;  
		//3.查询当前页日志记录
		Integer pageSize = 3;//页面大小
		Integer startIndex =(pageCurrent-1)*pageSize;
		List<SysLog> objs = 
				sysLogDao.findPageObjects(name, startIndex , pageSize);
		//4.封装查询结果并返回
		return new PageObject<SysLog>(objs.size(), objs, pageSize, pageCurrent) ;   
	}
	
}
