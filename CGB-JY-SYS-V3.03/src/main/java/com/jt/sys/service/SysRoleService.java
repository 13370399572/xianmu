package com.jt.sys.service;



import java.util.List;
import java.util.Map;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.entity.SysRole;

public interface SysRoleService {
   PageObject<SysRole> findPageObjects( String name,Integer pageCurrent);
   int  deleteObject(Integer id);
     int saveObject (SysRole entity,Integer... menuIds);
	/* Map<String, Object>findObjectById(Integer id); */
     
     SysRoleMenuResult findObjectById(Integer id);

      int updateObject(SysRole entity,Integer... menuIds);
      List<CheckBox> findObjects();
}
