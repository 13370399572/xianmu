package com.jt.sys.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysMenuDao sysMenuDao;
    
    
    
    
   @Override
public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
	HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
	//加密方法
	cMatcher.setHashAlgorithmName("MD5");
	//加密次数
	cMatcher.setHashIterations(1);
	
	super.setCredentialsMatcher(cMatcher);
}
    //认证管理信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//客户端传过来的token
		//获取客户端用户提交的用户信息
		UsernamePasswordToken upToken=
				(UsernamePasswordToken)token;
		//基于用户名查询用户
		String username=upToken.getUsername();
	     
		SysUser user=sysUserDao.findUserByUserName(username);
		if (user==null) {
			throw new UnknownAccountException();
		}
		if (user.getValid()==0) {
			throw new ServiceException("您已经被禁用");
		}
		//证明用户是否存在
		
		
		
		ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
		//对用户信息进行封装
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
		
		return info;
	}
	//授权管理系统
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户对象(此对应依赖于认证是封装的用户的身份)
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		//基于用户查找角色id
		List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(user.getId());
		//基于角色id查找菜单id
		Integer [] array= {};
		//需要数住的参数 所以用toarray方法把集合转成数组
		List<Integer> menuIds=sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		//基于菜单id查找权限标识
		List<String> permisssions=sysMenuDao.findPermissions(menuIds.toArray(array));
		//封装权限信息
		
		Set<String> pSet=new HashSet<String>();
		for(String permission:permisssions) {
			if (!StringUtils.isEmpty(permission)) {
				pSet.add(permission);
			}
		}
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
         info.setStringPermissions(pSet);
		return info;
	}

}
