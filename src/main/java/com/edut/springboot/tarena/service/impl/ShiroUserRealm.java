package com.edut.springboot.tarena.service.impl;


import java.util.HashSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edut.springboot.tarena.dao.SysUserDao;
import com.edut.springboot.tarena.pojo.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		
		HashSet<String> permissions = new HashSet<>();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ; 
		info.setStringPermissions(permissions);
		return info ;
	}

	@Autowired
	private SysUserDao sysUserDao ; 
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//realm 领域对象 - shiro - 程序和数据之间的桥梁 ==> service
		//token 凭证 ==> 用户发送的登录请求的封装
		//AuthenticationInfo ==> 认证信息 ==> 用来和用户发送的凭证做对比的
		
		//Credential 证书
		//principal 当事人
		
		/*
		 * 获取用户
		 */
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		final String username = usernamePasswordToken.getUsername();
		final SysUser sysUser = sysUserDao.findUserByUsername(username);
		
		/*
		 * 判断
		 */
		/*
		 * 在 Authenticator 中可以看到可以抛出的异常
		 * @see ExpiredCredentialsException
	     * @see IncorrectCredentialsException
	     * @see ExcessiveAttemptsException
	     * @see LockedAccountException
	     * @see ConcurrentAccessException
	     * @see UnknownAccountException
		 */
		
		if(sysUser==null) throw new UnknownAccountException("用户不存在！") ;
		if(sysUser.getValid()==0) throw new LockedAccountException("用户被禁用！");
		
		/*
		 * 封装凭证
		 */
		Object hashedCredentials = sysUser.getPassword() ;
		ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getSalt());
		String realmName = sysUser.getUsername();
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(
						sysUser, 
						hashedCredentials, 
						credentialsSalt, 
						realmName) ; 
		
		return info;
	}
	
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		
		HashedCredentialsMatcher hashedCredentialMatcher = 
				new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME) ;
		
		//hashedCredentialMatcher.setHashIterations(1);
		
		super.setCredentialsMatcher(hashedCredentialMatcher);
	}

}
