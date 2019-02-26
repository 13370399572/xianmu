package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysDept;

public interface SysDeptDao {
    
	
	List<Map<String,Object>> findObjects();
	 int getChildCount(@Param("id")Integer id);
	 int deleteObject(@Param("id") Integer id);
	 List<Node> findZtreeDeptNodes();
	 int insertObject(SysDept entity);
	 int updateObject(SysDept entity);
	 
	SysDept findById( @Param("deptId")Integer id);
}
