package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceimpl implements SysMenuService {
	@Autowired
   private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObject() {
		
		return sysMenuDao.findObject();
	}
	@Override
	public int deleteObject(Integer id) {
		if (id==null||id<=0) {
			throw new ServiceException("请选择");}
		int count=   sysMenuDao.getChildCount(id);
		if (count>0) {
			throw new ServiceException("请先选择删除子菜单");
		}
		   int  rows=sysMenuDao.deleteObject(id);
		if (rows==0) {
			throw new ServiceException("此菜单可能不存在");
			
			
		}
		 sysRoleMenuDao.deleteObjectsByMenuId(id);
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return  sysMenuDao.findZtreeMenuNodes();
	}
	@Override
	public int insertObject(SysMenu entity) {
	      if (entity==null) {
	    	  throw new ServiceException("新增對象不能為空");

		}
	       if (StringUtils.isEmpty(entity.getName())) {
	    	   throw new ServiceException("菜單名不能為空");
			
		}
	       int rows;
	       try {
	       rows=sysMenuDao.insertObject(entity) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失敗");
		}
	     return rows;
	}
	@Override
	public int updateObjects(SysMenu entity) {
		if (entity==null) {
			throw new ServiceException("保存对象不能为空");
			
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("菜单名不能为空");
			
		}
		int rows=sysMenuDao.updateObjects(entity);
		if (rows==0) {
			throw new ServiceException("纪录可能已经不存在");
			
			
		}
		return rows;
	}

}
