package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService; 
	
	@RequestMapping("doMenuListUI")
	public String doMenuListUI(){
	return "sys/menu_list";
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(sysMenuService.findObject());
	}
	   @RequestMapping("doDeleteObject")
	   @ResponseBody
	public JsonResult doDeleteObject( Integer id) {
		   sysMenuService.deleteObject(id);
		   return new JsonResult("delete OK");
	}
	   @RequestMapping("doMenuEditUI")
		 public String doMenuEditUI(){
			 return "sys/menu_edit";
		 }
	   @RequestMapping("doFindZtreeMenuNodes")
	   @ResponseBody
	   public JsonResult doFindZtreeMenuNodes() {
		   return new JsonResult(sysMenuService.findZtreeMenuNodes()); 
	   }
	   @RequestMapping("doSaveObject")
		 @ResponseBody
		  public JsonResult doSaveObject (SysMenu entity) {
			  sysMenuService.insertObject(entity);
			  return new JsonResult("save ok");
		  }
	   @RequestMapping("doUpdateObject")
	   @ResponseBody
	   public JsonResult doUpdateObject(SysMenu entity) {
		   sysMenuService.updateObjects(entity);
		   return new JsonResult("update ok");
	   }
}
