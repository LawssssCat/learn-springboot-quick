package com.edut.springboot.vedio.test;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DataSourceTest {

	@Autowired
	private DataSource dataSource ;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ; 
	
	@Test
	public void test_getConnection() throws SQLException {
		log.info("############ datasource.class="+dataSource.getClass().getName());
		Connection conn = dataSource.getConnection();
		log.info("############ conn="+conn.toString());
		conn.close();
	}

	@Test
	public void test_query() {
		String sql = "select * from sys_configs";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql );
		list.forEach(l -> log.info(l.toString()));
	}
}
