package com.jt.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
     @RequestMapping("doRoleListUI")
	 public String doRoleListUI() {
		 return "sys/role_list";
	 }
     @RequestMapping("doFindPageObjects")
     @ResponseBody
     public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
    	 
     PageObject<SysRole> pageobject=sysRoleService.findPageObjects(name, pageCurrent);
    	 return new JsonResult(pageobject);
     }
       @RequestMapping("doDeleteObject")
       @ResponseBody
        public JsonResult doDeleteObject(Integer id) {
        	sysRoleService.deleteObject(id);
        	return new JsonResult("delete Ok");
        	
        }
       @RequestMapping("doRoleEditUI")
       public String doRoleEditUI(){
       		return "sys/role_edit";
       }
       @RequestMapping("doSaveObject")
       @ResponseBody
       public JsonResult doSaveObject(SysRole entity,Integer... menuIds) {
    	   sysRoleService.saveObject(entity, menuIds);
    	   return new JsonResult("save ok");
       }
	/*
	 * @RequestMapping("doFindObjectById")
	 * 
	 * @ResponseBody public JsonResult doFindObjectById(Integer id) { Map<String,
	 * Object>map =sysRoleService.findObjectById(id); return new JsonResult(map); }
	 */
       @RequestMapping("doFindObjectById")
       @ResponseBody
       public JsonResult doFindObjectById(Integer id) {
    	   return new JsonResult(sysRoleService.findObjectById(id));
       }
       @RequestMapping("doUpdateObject")
 	  @ResponseBody
        public JsonResult doUpdateObject(SysRole entity,
        		Integer... menuIds) {
        	sysRoleService.updateObject(entity, menuIds);
        	return new JsonResult("update ok");
        }

       @RequestMapping("doFindObjects")
     @ResponseBody
       public JsonResult doFindObjects() {
    	  return new JsonResult(sysRoleService.findObjects()); 
       }
}
