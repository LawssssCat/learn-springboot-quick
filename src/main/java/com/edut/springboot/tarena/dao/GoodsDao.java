package com.edut.springboot.tarena.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.edut.springboot.tarena.pojo.Goods;

@Mapper
public interface GoodsDao {
	
	int insertObject(Goods ...  goods) ;
	
	@Select("select * from tb_goods")
	List<Goods> findGoods();

	@Delete("delete from tb_goods where id = #{id}")
	Integer deleteGoodsById(Long id);

}
