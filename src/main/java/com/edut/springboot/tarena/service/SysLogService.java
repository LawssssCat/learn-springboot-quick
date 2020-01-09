package com.edut.springboot.tarena.service;


import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.pojo.SysLog;

public interface SysLogService {
    /**
	 * 通过此方法实现分页查询操作
	 * @param name 基于条件查询时的参数名
	 * @param pageCurrent 当前的页码值
	 * @return 当前页记录+分页信息
	 */
	PageObject<SysLog> findPageObject(String name , Integer pageCurrent) ;
	
	
	public int deleteObject(Long ... ids)   ;
}
