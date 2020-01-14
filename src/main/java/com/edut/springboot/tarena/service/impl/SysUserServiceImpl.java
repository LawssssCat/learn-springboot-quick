package com.edut.springboot.tarena.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.common.config.PaginationProperties;
import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.common.vo.SysUserDeptVo;
import com.edut.springboot.tarena.dao.SysUserDao;
import com.edut.springboot.tarena.dao.SysUserRoleDao;
import com.edut.springboot.tarena.pojo.SysUser;
import com.edut.springboot.tarena.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao ;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao ; 
	
	@Autowired
	private PaginationProperties paginationProperties ; 
	
	public void isExist(String columnName , String columnValue) {
		int rows = sysUserDao.isExist(columnName, columnValue);
		Assert.isServiceValid(rows!=0, "已存在！");
	}
	

	@Override
	public PageObject<SysUserDeptVo> findPageObjects(Integer pageCurrent, String username) {
		/**
		 * 参数校验
		 */
		//Assert.isEmpty(username, "用户名不能为空！ %_%");
		Assert.isErrorInteger(pageCurrent, "页码异常！ $_$");
		Integer pageSize = paginationProperties.getPageSize();
		Assert.isErrorInteger(pageSize , "页面参数") ;
		Integer startIndex = paginationProperties.getStartIndex(pageCurrent); 
		/**
		 * 执行查询 $ 校验
		 */
		Integer rowCount = sysUserDao.getRowCount(username) ;
		Assert.isArgumentValid(rowCount==0, "没有数据源 ~_~");
		
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username , pageSize , startIndex )  ;
		Assert.isListEmpty(records, "数据可能不存在了！");
		
		return new PageObject<SysUserDeptVo>(rowCount, records, pageSize, pageCurrent);
	}

	@Override
	public int validById(Integer id, Integer valid) {
		//1. 参数校验 
			// id ==null <1 id值不正确
		Assert.isArgumentValid(id==null||id<1, "id值不正确！");
			//valid 1 0 状态值不正确
		Assert.isArgumentValid(valid!=1&&valid!=0, "状态异常！");
		//TODO modifiedUser
		String modifiedUser = null ;
		//2. 执行更新并校验
		int rows = sysUserDao.validById(id, valid, modifiedUser ) ;
			//rows==0 记录可能不存在了！
		Assert.isServiceValid(rows==0, "记录可能不存在了！");
		//3. 返回结果
		return rows ;
	}

	@Override
	public int saveObject(SysUser sysUser, Integer[] roleIds) {
		//校验
		Assert.isArgumentValid(sysUser==null, "请填入数据！");
		Assert.isEmpty(sysUser.getUsername(), "用户名不能为空！");
		Assert.isEmpty(sysUser.getPassword(), "密码不能为空");
		Assert.isArgumentValid( roleIds==null || roleIds.length==0 , "必须选择一个角色！");
		
		//校验重复
		isSame(sysUser.getUsername(),sysUser.getEmail() , sysUser.getMobile());
		
		
		//2.保存用户自身信息
        //2.1对密码进行加密
		/* 
		 spring框架也有：
		 
		  DigestUtils.md5DigestAsHex((password+salt).getBytes()) ; // 
		 
		 但这里没有指定加密次数
		 因此，用下面第三方框架 shiro
		 */
		String source = sysUser.getPassword() ; 
		String salt = UUID.randomUUID().toString() ; 
    	//1. 不可逆
    	//2. 相同字段加密相同，不同字段加密（超小概率重复）
		SimpleHash sh = new SimpleHash( // Shiro框架
				"MD5",// algorithmName 算法
				source, // 原密码
				salt,  // 盐值
				1) ; //hashIterations 表示加密次数
		sysUser.setSalt(salt);
		sysUser.setPassword(sh.toHex());//将密码加密结果转换为16进制并存储到entity内
		
		//执行、校验
		int rows = sysUserDao.insertObject(sysUser);
		Integer id = sysUser.getId();
		Assert.isServiceValid(rows==0||id==null|| id<1, "存储异常！");
		
		rows = sysUserRoleDao.insertObjects(id , roleIds) ;
		Assert.isServiceValid(rows==0, "存储异常！");
		
		//返回结果
		return rows;
	}

	@Override
	public Map<String, Object> findObjectById(Integer id) {
		/**
		 * 验证
		 */
		Assert.isArgumentValid(id==null || id<1, "请先选择要修改项");
		/**
		 * 执行、验证
		 */
		SysUserDeptVo user = sysUserDao.findObjectById(id);
		Assert.isServiceValid(user==null, "记录可能不存在了！");
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(id) ; 
		/**
		 * 返回
		 */
		Map<String, Object > map = new HashMap<>() ;
		map.put("user",user );
		map.put("roleIds" , roleIds) ;
		return map;
	}

	@Override
	public int updateObject(SysUser sysUser, Integer[] roleIds) {
		//校验1
		Assert.isArgumentValid(sysUser==null, "请输入");
		Assert.isEmpty(sysUser.getUsername(), "用户名不能为空");
		Assert.isArgumentValid(roleIds==null||roleIds.length==0, "必须制定权限");
		
		//校验2 重复
		isSame(sysUser.getUsername(),sysUser.getEmail() , sysUser.getMobile());
		
		
		int rows = sysUserDao.updateObejct(sysUser);
		Assert.isServiceValid(rows==0, "数据可能不存在了！");
		
		sysUserRoleDao.deleteObjectsByUserId(sysUser.getId());
		sysUserRoleDao.insertObjects(sysUser.getId(), roleIds);
		
		return rows;
	}

	private void isSame(String username, String email, String mobile) {
		Assert.isServiceValid(username!=null&&sysUserDao.isExist("username", username)!=0, "用户名已存在！"); ; 
		Assert.isServiceValid(email!=null&&sysUserDao.isExist("email", email)!=0, "用户名已存在！"); ; 
		Assert.isServiceValid(mobile!=null&&sysUserDao.isExist("mobile", mobile)!=0, "用户名已存在！"); ;		
	} 
	
}
