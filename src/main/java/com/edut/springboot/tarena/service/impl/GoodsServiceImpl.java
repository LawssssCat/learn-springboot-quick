package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.edut.springboot.tarena.dao.GoodsDao;
import com.edut.springboot.tarena.pojo.Goods;
import com.edut.springboot.tarena.service.GoodsService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("goodsService") 
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private Gson gson ; 
	
	@Autowired
	private GoodsDao goodDao ; 
	
	@Override
	public String doFindGoods() {
		StopWatch watch = new StopWatch();
		watch.start();
		List<Goods> goods = goodDao.findGoods() ;
		watch.stop();
		log.info("" + watch.getTotalTimeMillis());
		
		String json = gson.toJson(goods); 
		return json;
	}

}
