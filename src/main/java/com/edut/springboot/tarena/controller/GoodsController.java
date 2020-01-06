package com.edut.springboot.tarena.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edut.springboot.tarena.pojo.Goods;
import com.edut.springboot.tarena.service.GoodsService;

@RequestMapping("/goods")
@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService ; 
	
	@RequestMapping("/doGoodsUI")
	public String doGoosUI(Model model) {
		return "/pages/goods-ajax-jquery" ; 
	}
	
	@ResponseBody
	@RequestMapping("/doFindGoods")
	public String doFindGoods(Model model) {
		return goodsService.doFindGoods() ; 
	}
	
//	@RequestMapping("/getGoods") 
//	public String getGoods(Model model ) {
//		List<Goods> goods = goodsService.getGoods() ;
//		model.addAttribute("goods", goods) ; 
//		return "goods" ; 
//	}
	
	@ResponseBody
	@RequestMapping("/deleteGoods")
	public String deleteGood(@RequestParam(name = "id") Long goodsId,Model model ) {
		Integer rows = goodsService.deleteGoods(goodsId);
		model.addAttribute("successMsg", "删除 "+rows+" 行数据") ; 
		return "delete ok!"; 
	}
	
	@ResponseBody
	@RequestMapping("/addGoods")
	public String addGoods(Goods goods , Model model ) {
		goodsService.addGoods(goods) ;
		model.addAttribute("successMsg", "成功添加 1 行数据") ; 
		return "save ok!" ;
	}
}
