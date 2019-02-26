package com.jt.sys.service;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;

public interface SysLogService {
	
	PageObject<SysLog> findPageObject(
			
@Param("username")String username,
@Param("pageCurrent")Integer pageCurrent);

	int deleteObjects(Integer... ids);

}
