package com.edut.springboot.tarena.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edut.springboot.tarena.pojo.SysRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SysRoleDaoTest {

	@Autowired
	private SysRoleDao sysRoleDao ; 
	
	@Test
	public void  test_getRowCount() {
		int rows = sysRoleDao.getRowCount("系统"); //系统管理员
		log.info("rows="+rows); // 1
		rows = sysRoleDao.getRowCount("") ; 
		log.info("rows="+rows); // 0
		rows = sysRoleDao.getRowCount("sdfafads") ; 
		log.info("rows="+rows); // 0
	}
	
	@Test
	public void test_findObjects() {
		 List<SysRole> roles = sysRoleDao.findObjects("", 0, 2); 
		 roles.forEach(r -> log.info(r.toString()));
	}
}
