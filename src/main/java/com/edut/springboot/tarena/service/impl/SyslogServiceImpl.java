package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.config.PaginationProperties;
import com.edut.springboot.tarena.common.exception.ServiceException;
import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.dao.SysLogDao;
import com.edut.springboot.tarena.pojo.SysLog;
import com.edut.springboot.tarena.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service	
public class SyslogServiceImpl implements  SysLogService {
	
	@Autowired
	private PaginationProperties paginationProperties ; 
	
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
		Assert.isArgumentValid(pageCurrent == null || pageCurrent<1 , "页码值不正确, pageCurrent="+pageCurrent);
		
		//2.基于用户名检查总记录数并校验
		int rowCount = sysLogDao.getRowCount(name);
		Assert.isServiceValid(rowCount == 0, "没有数据");
		
		//3.查询当前页日志记录
		Integer pageSize = paginationProperties.getPageSize();//页面大小
		//计算开始查询index
		Integer startIndex = paginationProperties.getStartIndex(pageCurrent);
		
		List<SysLog> objs = 
				sysLogDao.findPageObjects(name, startIndex , pageSize);
		//4.封装查询结果并返回
		return new PageObject<SysLog>(rowCount, objs, pageSize, pageCurrent) ;   
	}

	@Override
	public int deleteObject(Long... ids)  {
	
		Assert.isArgumentValid(ids==null|| ids.length ==0, "请选中一个");
		int rows = 0 ; 
		try {
			rows = sysLogDao.deleteObjects(ids); 
			log.debug("删除数据,rows="+rows);
		}catch (Throwable e) {
			e.printStackTrace();
			//发出报警信息(例如给运维人员发短信)
			throw new ServiceException("系统故障，正在维护中....") ; 
		}
		
		Assert.isServiceValid(rows == 0, "数据可能不存在");
		return rows;
	}
	
}
