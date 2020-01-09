package com.edut.springboot.tarena.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.pojo.SysLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SysLogServiceTest {
	
	@Autowired
	private SysLogService sysLogService; 

	@Test
	public void test_findPageObject() {
		PageObject<SysLog> obj = sysLogService.findPageObject("a", 1);
		log.info(obj.toString());
	}
}
