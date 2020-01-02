package com.edut.springboot.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.edut.springboot.tarena.pojo.Goods;

@Mapper
public interface GoodsDao {

	@Select("select * from tb_goods")
	List<Goods> findGoods();

}
