package com.edut.springboot.tarena.dao;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edut.springboot.tarena.pojo.Goods;

@SpringBootTest
public class GoodsDaoTest {
	
	@Autowired
	private GoodsDao goodsDao ; 

	@Test
	public void testfindGoods() {
		List<Goods> goods = goodsDao.findGoods();
		for (Goods good : goods) {
			System.out.println(good);
		}
	}
	
	@Test
	public void testInsertGoods() {
		Goods goods = new Goods(111l, "11aa", "bb" , new Date( new  java.util.Date().getTime())) ; 
		goodsDao.insertObject(goods) ; 
		
		System.out.println("success");
	}
}
