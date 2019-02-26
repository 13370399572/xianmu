package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysDept;
import com.jt.sys.service.SysDeptService;

@RequestMapping("/dept/")
@Controller
public class SysDeptController  {
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("doDeptListUI")
	public String doDeptListUI(){
	return "sys/dept_list";
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	 public JsonResult doFindObjects() {
		 return new JsonResult(sysDeptService.findObjects());
	 }
	@RequestMapping("doDeleteObject")
	 @ResponseBody
	 public JsonResult doDeleteObject(Integer id){
		 sysDeptService.deleteObject(id);
		 return new JsonResult("delete OK");
	 }

	 @RequestMapping("doDeptEditUI")
	 public String doDeptEditUI(){
		 return "sys/dept_edit";
	 }
	   @RequestMapping("doFindZtreeDeptNodes") 
	   @ResponseBody
	 public JsonResult  doFindZtreeDeptNodes() {
		 return new JsonResult(sysDeptService.findZtreeDeptNodes());
	 } 
	   @RequestMapping("doSaveObject")
		@ResponseBody
	   public JsonResult doSaveObject(SysDept entity) {
		   sysDeptService.saveObject(entity);
		   return new JsonResult("save ok");
	   }
	   @RequestMapping("doUpdateObject")
	   @ResponseBody
	    public JsonResult doUpdateObject(SysDept entity) {
	    	sysDeptService.updateObject(entity);
	    	return  new JsonResult("update ok");  
	    }
}
