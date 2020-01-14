package com.edut.springboot.tarena.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysUserDaoTest {
	
	@Autowired
	private SysUserDao sysUserDao ; 

	@Test
	public void test_verificationModule() {
		
		String columnName = "username";
		String columnValue = "aaaaaa" ;
		int rows = sysUserDao.verificationModule(columnName , columnValue);
		System.out.println("username - rows="+rows);

		rows = sysUserDao.verificationModule("mobile" , "13624356789");
		System.out.println("mobile - rows="+rows);
		
		rows = sysUserDao.verificationModule("email" , "admin@t.cn");
		System.out.println("email - rows="+rows);
	}
}
