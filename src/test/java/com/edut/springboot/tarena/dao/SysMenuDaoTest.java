package com.edut.springboot.tarena.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SysMenuDaoTest {
	
	@Autowired
	private SysMenuDao sysMenuDao ; 

	@Test
	public void test_findObjects() {
		sysMenuDao.findObjects()
		.forEach(map -> log.info(map.toString()));
	}
}
