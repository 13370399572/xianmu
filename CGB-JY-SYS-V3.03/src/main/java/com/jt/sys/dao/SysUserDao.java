package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.SysUserDeptResult;
import com.jt.sys.entity.SysUser;

public interface SysUserDao {
	int getUserCountByDeptId (@Param("DeptId")Integer DeptId);
	List<SysUserDeptResult> findPageObjects(
			@Param("username") String username,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize")Integer pageSize
			);
	//基于用户查询记录条数
	int getRowCount(@Param("username") String username);
     int valiById(
    		 @Param ("id")Integer id,
    		 @Param ("valid") Integer valid,
    		 @Param ("modifiedUser") String modifiedUser
    		 );
     
     int insertObject(SysUser entity);
     SysUserDeptResult findObjectById(@Param("id")Integer id);
     int updateObject(SysUser entity);
     SysUser findUserByUserName(String username);
}
