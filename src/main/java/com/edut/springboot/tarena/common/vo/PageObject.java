package com.edut.springboot.tarena.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * VO:(Value Object)
 * 借助此类封装从数据层获取的当前页记录以及
 * 总记录数等相关分页信息
 */
@Data
public class PageObject<T> implements Serializable {
	//为了在改变了类结构情况下，仍然能够序列化/反序列化
	private static final long serialVersionUID = -4636168396034352753L;
	/** 总行数 */
	private Integer rowCount = 0 ; //默认值其实没有也行
	/** 当前页要呈现的记录 */
	private List<T> records ;
	/** 总页数 */
	private Integer pageCount= 0 ;
	/** 页面大小(每页要呈现的记录数) */
	private Integer pageSize=3;
	/** 当前页页码值 */
	private Integer pageCurrent=1 ;
	public PageObject(Integer rowCount, List<T> records, Integer pageSize, Integer pageCurrent) {
		super();
		this.rowCount = rowCount;
		this.records = records;
		this.pageSize = pageSize;
		this.pageCurrent = pageCurrent;
		//计算分页的小算法
		//算法1
		this.pageCount=(rowCount-1)/pageSize + 1;
	}
	
	
}
