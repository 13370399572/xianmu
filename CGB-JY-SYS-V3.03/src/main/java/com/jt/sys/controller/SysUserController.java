package com.jt.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysUserDeptResult;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@RequestMapping("/user/")
@Controller
public class SysUserController {
	@Autowired
	private SysUserService sysUserService; 
	@RequestMapping("doUserListUI")
   public String doUserListUI() {
	   return "sys/user_list";
   }
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
		String username,Integer pageCurrent){
		PageObject<SysUserDeptResult> pageObject=
		sysUserService.findPageObjects(username,
pageCurrent);
		return new JsonResult(pageObject);
	}
	@RequestMapping("doValidById")
	@ResponseBody
	 public JsonResult doValiById(Integer id,
				Integer valid) {
		 //admin是修改的用户
		 sysUserService.valiById(id, valid,"admin");
		 return new JsonResult("权限修改成功");
	 }
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult  doSaveObject(SysUser entity,Integer[] roleIds) {
		sysUserService.saveObject(entity, roleIds);
		return new JsonResult("sava ok");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer userId){
		Map<String,Object> map=
		sysUserService.findObjectById(userId);
		return new JsonResult(map);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
	    SysUser entity,Integer[] roleIds){
		sysUserService.updateObject(entity,
				roleIds);
		return new JsonResult("update ok");
	
	}
	   @RequestMapping("doLogin")
	   @ResponseBody
	public JsonResult doLogin(String username,String password,HttpSession httpSession) {
		Subject subject =SecurityUtils.getSubject();
		UsernamePasswordToken token =new UsernamePasswordToken(
			username,password);
		httpSession.setAttribute("username",username);
		subject.login(token);
		return new JsonResult("login ok");
		
	}   
	   @RequestMapping("Session")
	   @ResponseBody
	   public JsonResult Session(HttpSession httpSession) {
		 String  username=(String)httpSession.getAttribute("username");
	      return new JsonResult(username);
	   }
}
