package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuService {
	List<Map<String, Object>> findObject();
	 int deleteObject(Integer id);
	 List<Node>findZtreeMenuNodes();
	 int  insertObject(SysMenu entity);
	 int updateObjects(SysMenu entity);
}
