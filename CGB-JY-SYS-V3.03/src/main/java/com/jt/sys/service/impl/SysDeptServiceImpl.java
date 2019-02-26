package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysDeptDao;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.entity.SysDept;
import com.jt.sys.service.SysDeptService;
@Service
public class SysDeptServiceImpl implements SysDeptService {
 @Autowired
private SysUserDao sysUserDao;
	
	@Autowired
  private SysDeptDao sysDeptDao;
  
	public List<Map<String, Object>> findObjects() {
		
		return sysDeptDao.findObjects();
	}

	@Override
	public int deleteObject(Integer id) {
		if (id==null||id<=0) {

		}
		int ch=sysDeptDao.getChildCount(id);
		if (ch>0) {
			throw new ServiceException("有子元素不能删除");
			
		}
		int user=sysUserDao.getUserCountByDeptId(id);
		if (user>0) {
			throw new ServiceException("此部门有员工,请先删除");
			
			
		}
		int row =sysDeptDao.deleteObject(id);
		if (row==0) {
			
			throw new ServiceException("此信息可能已经不存在");
		}
		return row;
	}

	@Override
	public List<Node> findZtreeDeptNodes() {
		
		return sysDeptDao.findZtreeDeptNodes();
	}

	@Override
	public int saveObject(SysDept entity) {
		if (entity==null) {
			throw new ServiceException("保存对象不能空");
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("部门名不能为空");
		}
		      int rows;
		      try {
		    	  rows =sysDeptDao.insertObject(entity);
			} catch (Exception e) {
				e.printStackTrace();
			throw new ServiceException("保存失败");
			}
		     
		return  rows;
	}

	@Override
	public int updateObject(SysDept entity) {
		if (entity==null) {
			throw new ServiceException("保存的对象不能为空");
			
		}
		if (StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("部门名称不能为空");
		}
		int rows = sysDeptDao.updateObject(entity);
		if (rows==0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;
	}

	
        
}
