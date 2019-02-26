package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;
@Service
public class SysLogServiceimpl implements SysLogService {
   @Autowired
	public SysLogDao sysLogDao;
	@Override
	public PageObject<SysLog> 
	findPageObject(
	String username, Integer pageCurrent) {
		if (pageCurrent==null|| pageCurrent<1) {
	     throw new IllegalArgumentException("页码值不正确");
		}
	int rowCount=sysLogDao.getRowCount(username);
	if (rowCount==0) {
	throw new ServiceException("记录不存在");	
	}
	int pageSize=3;
	int startIndex=(pageCurrent-1)*pageSize;
	  List<SysLog> records=sysLogDao.findPageObjects(username, startIndex, pageSize);
	PageObject<SysLog> po =new PageObject<>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		int pageCount =rowCount/pageSize;
		if(rowCount%pageSize!=0)pageCount++;
		po.setPageCount(pageCount);
		
		return po; 
	}
	@Override
	public int deleteObjects(Integer... ids) {
		if (ids==null||ids.length==0) {
			throw new RuntimeException("请选择一个");
		}
		int rows;
		try {
	rows	=	sysLogDao.deleteObject(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统故障,正在恢复");
		}
		if (rows==0) {
			
			throw new ServiceException("纪录不存在");
		}
		return rows;
	}
    
}
