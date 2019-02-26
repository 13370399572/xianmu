package com.jt.sys.service.impl;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;
@Service
public class SysRoleServiceimpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	

	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		if (pageCurrent==null|| pageCurrent<1) {
			throw new ServiceException("页码值不正确");
		}
		  int rowCount= sysRoleDao.getRowCount(name);
		  System.out.println(rowCount);
		  if (rowCount==0) {
			throw new ServiceException("纪录不存在");
		}
		    int pageSize=3;
			int startIndex=(pageCurrent-1)*pageSize;
			List <SysRole> records=sysRoleDao.findPageObjects(name, startIndex, pageSize);
			PageObject<SysRole> po=new PageObject<SysRole>();
			//设置也面总数
			int pageCount =rowCount/pageSize;
			if(rowCount%pageSize!=0)pageCount++;
			po.setPageCount(pageCount);
			//页面内容
			po.setRecords(records);
			//当前页页码值
			po.setPageCurrent(pageCurrent);
			//等前页面大小
			po.setPageSize(pageSize);
			//纪录总数
			po.setRowCount(rowCount);
			

		return po;
	}


	@Override
	public int deleteObject(Integer id) {
		if (id==null||id<1) {
		   throw new ServiceException("id的值不正确,id="+id);	
		}
		int rows=sysRoleDao.deleteObject(id);
		if (rows==0) {
			throw new ServiceException("数据可能已经不存在");
		}
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		
		return rows;
	}


	@Override
	public int saveObject(SysRole entity, Integer... menuIds) {
		if (entity==null) {
			throw new ServiceException("保存数据不能为空");
				}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("角色名不能为空");
		}
		if (menuIds==null||menuIds.length==0) {
			throw new ServiceException("必须为角色赋予权限");
		}
		   int rows=sysRoleDao.insertObject(entity);
		   sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		return rows;
	}


	@Override
	public SysRoleMenuResult findObjectById(Integer id) {
    	//1.合法性验证
    	if(id==null||id<=0)
    	throw new ServiceException("id的值不合法");
    	//2.执行查询
    	SysRoleMenuResult result=sysRoleDao.findObjectById(id);
  	//3.验证结果并返回
    	if(result==null)
    	throw new ServiceException("此记录已经不存在");
    	return result;
	}


	@Override
	public int updateObject(SysRole entity, Integer...  menuIds) {
		if (entity==null) {
			throw new ServiceException("更新的对象不能为空");
		}
		if (entity.getId()==null) {
			throw new ServiceException("id的值不能为空");
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("角色名不能为空");
		}
		 if (menuIds==null||menuIds.length==0) {
			throw new ServiceException("必须为角色指定一个权限");
		}
		  int rows=sysRoleDao.updateObject(entity);
		  if (rows==0) {
			throw new ServiceException("对象可能已经不存在");
		}
		    sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		    sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		    
		return rows;
	}


	@Override
	public List<CheckBox> findObjects() {
		
		return sysRoleDao.findObjects();
	}


	/*
	 * @Override public Map<String, Object> findObjectById(Integer id) { if
	 * (id==null||id<0) { throw new ServiceException("id值不和法"); } SysRole
	 * role=sysRoleDao.findObjectById(id); if (role==null) { throw new
	 * ServiceException("此纪录不存在"); } List<Integer>
	 * menuIds=sysRoleMenuDao.findMenuIdsByRoleId(id); Map<String, Object> map=new
	 * HashMap<String, Object>(); map.put("role",role); map.put("menuIds",menuIds);
	 * return map; }
	 */

	
	

}
