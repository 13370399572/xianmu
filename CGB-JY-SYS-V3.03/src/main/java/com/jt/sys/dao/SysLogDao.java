package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysLog;

public interface SysLogDao {
 //按用户名获取记录总数
	int getRowCount(@Param("username") String username);
	
	List<SysLog> findPageObjects(
			@Param("username") String username,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize")	Integer pageSize);
   int	deleteObject(@Param("ids") Integer... ids);
}
