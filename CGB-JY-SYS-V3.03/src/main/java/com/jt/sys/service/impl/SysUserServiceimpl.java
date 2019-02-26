package com.jt.sys.service.impl;




import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysUserDeptResult;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;

@Service
public class SysUserServiceimpl implements SysUserService {
    @Autowired
	private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
	public PageObject<SysUserDeptResult> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent==null||pageCurrent<=0) {
			throw new ServiceException("页码值不存在"
					+ "");
		}
		int rowCount=sysUserDao.getRowCount(username);
		if (rowCount==0) {
			throw new ServiceException("此记录不存在");
		}
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		
		List<SysUserDeptResult> records=
				sysUserDao.findPageObjects(
			username, startIndex, pageSize);
		PageObject<SysUserDeptResult> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRecords(records);
		pageObject.setPageSize(pageSize);
		int  pageCount= rowCount/pageSize;
		if (rowCount%pageSize!=0) {
			pageCount++;
		}
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount(pageCount);
		
		return pageObject;
	}
	
	@RequiresPermissions("sys:user:valid")
	public int valiById(Integer id, Integer valid, String modifiedUser) {
		if (id==null||id<=0) {
			throw new ServiceException("参数不合法,id="+id);

		}
		if (valid!=1&&valid!=0) {
			throw new ServiceException("参数不合法valid="+valid);
		}
		if (StringUtils.isEmpty(modifiedUser)) {
			throw new ServiceException("修改的用户不能为空");
		}
		int rows=0;
				try {
					rows=sysUserDao.valiById(id, valid, modifiedUser);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException("底层正在维护");
				}
				if (rows==0) {
					throw new ServiceException("此记录可能已经不存在了");
					
				}
		return rows;
	}
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		if (entity==null) {
			throw new ServiceException("保存对象不能为空");
			
		}
		if (StringUtils.isEmpty(entity.getUsername())) {
			throw new ServiceException("用户名不能为空");
		}
		if (StringUtils.isEmpty(entity.getPassword())) {
			throw new ServiceException("用户密码不能为空");
			
		}
		if (roleIds==null||roleIds.length==0) {
			throw new ServiceException("必须要为用户分配角色");
			
		}
		//盐值生产唯一的标识
		String salt=UUID.randomUUID().toString();
		entity.setSalt(salt);
		//MD5与颜值的加密密码
		//simpleHash 自带的类用来加密的
		
		SimpleHash sHash=
				new SimpleHash("MD5",entity.getPassword(),salt);
		entity.setPassword(sHash.toString());
		int rows=sysUserDao.insertObject(entity);
		sysUserRoleDao.insertObject(entity.getId(), roleIds);
		return rows;
	}
	@Override
	public Map<String, Object> findObjectById(Integer userId) {
	     if (userId==null||userId<=0) {
			throw new ServiceException("参数数据不合法,userId"+userId);
		}
	      SysUserDeptResult user=sysUserDao.findObjectById(userId);
	      if (user==null) {
			throw new ServiceException("此用户已经不存在");
		}
	       List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(userId);
	       Map<String,Object> map=new HashMap<>();
	       map.put("user", user);
	       map.put("roleIds", roleIds);
	 
		return map;
	}
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
	if (entity==null) {
		throw new ServiceException("用户信息不能为空");
	}
	if (StringUtils.isEmpty(entity.getUsername())) {
		throw new ServiceException("用户名不能为空");
	}
	if (roleIds==null||roleIds.length==0) {
		throw new ServiceException("用户必须选一个角色");
	}
	if (!StringUtils.isEmpty(entity.getPassword())) {
		//UUID 唯一的标识
		String salt=UUID.randomUUID().toString();
		SimpleHash hash=new SimpleHash("MD5",entity.getPassword(),salt);
		entity.setPassword(hash.toString());
		entity.setSalt(salt);
	}
	int rows=0;
	try {
		rows=sysUserDao.updateObject(entity);
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObject(entity.getId(), roleIds);
	} catch (Exception e) {
		e.printStackTrace();
		throw new ServiceException("服务器端异常,请稍后访问");
	}
		return rows;
	}

}
