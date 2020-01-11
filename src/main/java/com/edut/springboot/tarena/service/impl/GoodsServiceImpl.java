package com.edut.springboot.tarena.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.edut.springboot.tarena.dao.GoodsDao;
import com.edut.springboot.tarena.pojo.Goods;
import com.edut.springboot.tarena.service.GoodsService;
import com.google.gson.Gson;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service() 
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private Gson gson ; 
	
	@Autowired
	private GoodsDao goodDao ;
	

	@Override
	public String doFindGoods()   {
		try{Thread.sleep(777);}catch (Exception e) {}
		List<Goods> goods = getGoods() ;
		String json = gson.toJson(goods); 
		return json;
	}

	@Override
	public List<Goods> getGoods() {
		StopWatch watch = new StopWatch();
		watch.start();
		List<Goods> goods = goodDao.findGoods() ;
		watch.stop();
		log.info("@@@@  ####  ----  " + watch.getTotalTimeMillis());
		
		return goods;
	}

	@Override
	public Integer deleteGoods(Long goodsId) {
		int rows = goodDao.deleteGoodsById(goodsId);
		return rows ;
	}

	@Override
	public void addGoods(Goods goods) {
		goods.setId(System.nanoTime());
		goods.setCreatedTime(new Date(new java.util.Date().getTime()));
		
		goodDao.insertObject(goods) ; 
	}

}
