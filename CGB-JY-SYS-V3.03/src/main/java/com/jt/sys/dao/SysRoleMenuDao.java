package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {

	 int deleteObjectsByMenuId(@Param("menuId") Integer menuId);
           
	 int deleteObjectsByRoleId(
			 @Param("roleId")Integer roleId);
	 
	 int insertObject(
			 @Param("roleId") Integer roleId,
			 @Param("menuIds") Integer... menuIds
			 );
	 //基于角色id 查询菜单id的查询
	 List<Integer> findMenuIdsByRoleId(@Param("roleId") Integer roleId);
	//基于角色Id 查询菜单id授权 
	 List<Integer>findMenuIdsByRoleIds(
				@Param("roleIds")Integer[] roleIds);
} 
