package com.edut.springboot.tarena.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.functors.TruePredicate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edut.springboot.tarena.common.annotation.ClearCache;
import com.edut.springboot.tarena.common.annotation.RequiredCache;
import com.edut.springboot.tarena.common.annotation.RequiredLog;
import com.edut.springboot.tarena.common.config.PaginationProperties;
import com.edut.springboot.tarena.common.utils.Assert;
import com.edut.springboot.tarena.common.utils.ShiroUtils;
import com.edut.springboot.tarena.common.vo.PageObject;
import com.edut.springboot.tarena.common.vo.SysUserDeptVo;
import com.edut.springboot.tarena.dao.SysUserDao;
import com.edut.springboot.tarena.dao.SysUserRoleDao;
import com.edut.springboot.tarena.pojo.SysUser;
import com.edut.springboot.tarena.service.SysUserService;

@Transactional //类中全部方法 - 均开启Spring管理 Require - 事务
@Service
public class SysUserServiceImpl implements SysUserService {
	
	//@Autowired
	//private SecurityManager securityManager ; 
	
	@Autowired
	private SysUserDao sysUserDao ;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao ; 
	
	@Autowired
	private PaginationProperties paginationProperties ; 
	
	

	public void isExist(String columnName , String columnValue , Integer userId ) {
		int rows = sysUserDao.isExist(columnName, columnValue , userId);
		Assert.isServiceValid(rows>0, "已存在！");
	}

	@RequiresPermissions({"sys:user:view"}) //访问权限
	@Cacheable(value = {"page"} , key = "#pageCurrent")
	@Transactional(readOnly = true)
	@RequiredLog(operation = "分页查询")
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

	/*
	 * 加入在spring中，没有控制事务，现在有事务吗？
	 * 默认是mybatis框架在控制事务 ==》 mybatis无法控制业务层事务 ==》 在切面 AOP 中控制事务
	 */
	@RequiresPermissions({"sys:user:update"})
	@Caching(
			evict = {
				@CacheEvict(cacheNames = "page" , allEntries = true , beforeInvocation = true) ,
				@CacheEvict(cacheNames = "user" , key = "#id" , beforeInvocation = true)
			}  
		)
	@Transactional
	@RequiredLog(operation = "禁用按钮点击")
	@Override
	public int validById(Integer id, Integer valid) {
		//1. 参数校验 
			// id ==null <1 id值不正确
		Assert.isArgumentValid(id==null||id<1, "id值不正确！");
			//valid 1 0 状态值不正确
		Assert.isArgumentValid(valid!=1&&valid!=0, "状态异常！");
		
		String username = ShiroUtils.getUsername();
		String modifiedUser = username ;
		//2. 执行更新并校验
		int rows = sysUserDao.validById(id, valid, modifiedUser ) ;
			//rows==0 记录可能不存在了！
		Assert.isServiceValid(rows==0, "记录可能不存在了！");
		//3. 返回结果
		return rows ;
	}

	@RequiresPermissions({"sys:user:add"})
	@Caching(
			evict = {
				@CacheEvict(cacheNames = "page" , allEntries = true , beforeInvocation = true) 
			}  
		)
	//@ClearCache
	@Override
	public int saveObject(SysUser sysUser, Integer[] roleIds) {
		//校验
		Assert.isArgumentValid(sysUser==null, "请填入数据！");
		Assert.isEmpty(sysUser.getUsername(), "用户名不能为空！");
		Assert.isEmpty(sysUser.getPassword(), "密码不能为空");
		Assert.isArgumentValid( roleIds==null || roleIds.length==0 , "必须选择一个角色！");
		
		
		//重复校验
		isExist( "username",sysUser.getUsername(), null ) ;
		isExist( "email",sysUser.getEmail(), null );
		isExist(  "mobile",sysUser.getMobile(), null );
		
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
		
		sysUser.setModifiedUser(ShiroUtils.getUsername());
		//执行、校验
		int rows = sysUserDao.insertObject(sysUser);
		Integer id = sysUser.getId();
		Assert.isServiceValid(rows==0||id==null|| id<1, "存储异常！");
		
		//关系
		sysUserRoleDao.insertObjects(id , roleIds) ;
		Assert.isServiceValid(rows==0, "存储异常！");
		
		//返回结果
		return rows;
	}

	@RequiresPermissions({"sys:role:view"})
	@Cacheable(key = "#id" , cacheNames = "user")
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

	@RequiresPermissions({"sys:role:update"})
	@Caching(
			evict = {
				@CacheEvict(cacheNames = "page" , allEntries = true , beforeInvocation = true) ,
				@CacheEvict(cacheNames = "user" , key = "#sysUser.id" , beforeInvocation = true)
			}  
		)
	@RequiredLog(operation = "修改数据")
	//@ClearCache
	@Override
	public int updateObject(SysUser sysUser, Integer[] roleIds) {
		//校验1
		Assert.isArgumentValid(sysUser==null, "请输入");
		Assert.isEmpty(sysUser.getUsername(), "用户名不能为空");
		Assert.isArgumentValid(roleIds==null||roleIds.length==0, "必须制定权限");
		
		//重复校验
		isExist( "username", sysUser.getUsername(),sysUser.getId()) ;
		isExist("email", sysUser.getEmail(),  sysUser.getId());
		isExist("mobile", sysUser.getMobile(),  sysUser.getId());
		
		
		sysUser.setModifiedUser(ShiroUtils.getUsername());
		
		int rows = sysUserDao.updateObejct(sysUser);
		Assert.isServiceValid(rows==0, "数据可能不存在了！");
		
		//关系
		sysUserRoleDao.deleteObjectsByUserId(sysUser.getId());
		sysUserRoleDao.insertObjects(sysUser.getId(), roleIds);
		
		return rows;
	}

	
	@RequiredLog(operation = "用户登录")
	@Override
	public void doLogin(String username, String password , boolean isRememberMe) {
		//用户状态
		Subject subject = SecurityUtils.getSubject() ; 

		//凭证
		AuthenticationToken authenticationToken  = new UsernamePasswordToken(username , password,isRememberMe) ;
		
		//尝试登录
		subject.login(authenticationToken);
		//securityManager.login(subject, authenticationToken) ; 		
	} 
	
}
