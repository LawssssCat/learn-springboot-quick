package com.edut.springboot.tarena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.vo.Node;
import com.edut.springboot.tarena.dao.SysDeptDao;
import com.edut.springboot.tarena.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	
	@Autowired
	private SysDeptDao sysDeptDao ; 

	@Override
	public List<Node> findZTreeNodes() {
		return sysDeptDao.findZTreeNodes();
	}

}
