package com.edut.springboot.tarena.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edut.springboot.tarena.pojo.SysLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SysLogDaoTest {
	

	@Autowired
	private SysLogDao sysLogDao ; 
	
	@Test
	public void test_getRowCount() {
		String username = "a";
		int rows = sysLogDao.getRowCount(username ) ;
		log.info("rows="+rows);
	}
	
//	@Test
//	public void test_findPageObjects() {
//		Integer pageSize = 2;
//		Integer startIndex = 1 ; //数据库的从0开始
//		
//		//String username = "admin" ;
//		String username = null ;
//		List<SysLog> list = sysLogDao.findPageObjects(username, startIndex, pageSize);
//		list.forEach(l -> log.info(l.toString()));
//	}
	
	@Test
	public void test_deleteObjects() {
		int rows = sysLogDao.deleteObjects() ; 
		log.info("rows="+rows);
	}
}
