package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysUser;

public interface SysUserRoleDao {
	//删除角色的时候也必须要删除角色表里的用户
	int deleteObjectsByRoleId(@Param("roleId") Integer roleId);
	//添加用户
	int insertObject(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[] roleIds);
	//根据用户的id查询用户所拥有的角色
	List<Integer> findRoleIdsByUserId(@Param("userId")Integer userId);
	
	int deleteObjectsByUserId(@Param("userId")Integer userId);
    //授权管理查询
	//List<Integer> findRoleIdsByUserId(Integer id);
}
