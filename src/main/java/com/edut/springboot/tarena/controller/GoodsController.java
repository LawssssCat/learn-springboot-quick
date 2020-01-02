package com.edut.springboot.tarena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edut.springboot.tarena.service.GoodsService;

@RequestMapping("/goods")
@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService ; 
	
	@ResponseBody
	@RequestMapping("/doFindGoods")
	public String doFindGoods() {
		String json = goodsService.doFindGoods() ;
		return json ; 
	}
}
