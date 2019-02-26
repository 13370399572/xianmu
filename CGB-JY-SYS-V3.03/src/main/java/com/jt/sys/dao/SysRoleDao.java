package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.entity.SysRole;

public interface SysRoleDao {
     List <SysRole> findPageObjects(
    		 @Param("name")String name,
    		 @Param("startIndex")Integer startIndex,
    		 @Param("pageSize")Integer pageSize);
     
      int getRowCount(@Param("name")String name);
      int  deleteObject(@Param("id")Integer id);
      int insertObject(SysRole entity);
     // SysRole findObjectById(@Param("id") Integer id);
      SysRoleMenuResult findObjectById(@Param("id") Integer id);
      
      int updateObject(SysRole entity);
      List<CheckBox> findObjects();

}
