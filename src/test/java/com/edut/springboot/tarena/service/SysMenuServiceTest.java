package com.edut.springboot.tarena.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SysMenuServiceTest {
	
	@Autowired
	private SysMenuService sysMenuService ; 

	private void doDelete(Integer id ) {
		int rows =  -1 ;
		try {
			rows = sysMenuService.deleteObject(id) ; 
			log.info("rows="+rows);
		}catch(Exception e) {
			log.error(e.getMessage());
		}

	}
	@Test
	public void test_de() {
		doDelete(48) ; 
	}
	
	@Test
	public void test_deleteObject() {
		doDelete(-1); //fail 参数错误
		
		doDelete(48) ; // fail 不存在
		
		doDelete(47); //fail have child
		
		doDelete(130); // success !
		
		
		
		
		
	}
}
