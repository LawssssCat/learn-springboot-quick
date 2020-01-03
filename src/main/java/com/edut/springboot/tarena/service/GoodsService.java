package com.edut.springboot.tarena.service;

import java.util.List;

import com.edut.springboot.tarena.pojo.Goods;

public interface GoodsService {

	String doFindGoods();

	List<Goods> getGoods();

	Integer deleteGoods(Long goodsId);

	void addGoods(Goods goods);

}
