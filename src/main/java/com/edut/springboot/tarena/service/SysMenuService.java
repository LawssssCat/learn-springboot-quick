package com.edut.springboot.tarena.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.vo.JsonResult;
import com.edut.springboot.tarena.common.vo.Node;
import com.edut.springboot.tarena.dao.SysMenuDao;
import com.edut.springboot.tarena.dao.SysRoleMenuDao;
import com.edut.springboot.tarena.pojo.SysMenu;

public interface SysMenuService {
	
	JsonResult findObjects()  ; 
	
	int deleteObject( Integer id ) ;
	
	int saveObject(SysMenu entity) ;  
	
	List<Node> findZtreeMenuNodes() ;

	int updateObject(SysMenu entity);
}
