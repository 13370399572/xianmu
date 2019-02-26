package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuDao {
	List<Map<String, Object>> findObject();
    int getChildCount(@Param("id")Integer id);
    int deleteObject( @Param("id")  Integer id);
    List<Node>findZtreeMenuNodes();
     int  insertObject(SysMenu entity);
     int updateObjects(SysMenu entity);
     
     //基于菜单id 查询授权吗
     List<String> findPermissions(@Param("menuIds") Integer[] menuIds);
}
