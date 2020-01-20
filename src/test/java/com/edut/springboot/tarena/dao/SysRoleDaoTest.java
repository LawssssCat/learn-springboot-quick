package com.edut.springboot.tarena.dao;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edut.springboot.tarena.common.vo.SysRoleMenuVo;
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
	
//	@Test
//	public void test_findObjects() {
//		 List<SysRole> roles = sysRoleDao.findObjects("", 0, 2); 
//		 roles.forEach(r -> log.info(r.toString()));
//	}
	
	@Test
	public void test_insertObject() {
		String user = "username";
		SysRole sysRole = new SysRole()
		.setName("name")
		.setNote("Note")
		.setCreatedUser(user )
		.setModifiedUser(user);
		
		int i = sysRoleDao.insertObject(sysRole) ;
		log.info("rows=" + i);
		log.info("id="+ sysRole.getId());
	}
	
	@Test
	public void test_findObjectById() {
		Integer id1= 1 ; 
		Integer id = 45 ; //运维经理	运维经理..	2018/4/22 下午8:51:43	2018/4/22 下午8:51:43nullnull
		SysRoleMenuVo vo = sysRoleDao.findObjectById(id1 );
		log.info(vo.toString());
	}
}
