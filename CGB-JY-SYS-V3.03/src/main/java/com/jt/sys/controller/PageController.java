package com.jt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 在此控制器定义相关页面映射
 * @author Administrator
 */
@Controller
@RequestMapping("/")
public class PageController {
	  /**通过此方法返回首页页面*/
      @RequestMapping("doIndexUI")
	  public String doIndexUI(){
		  return "starter";
	  }
      /**通过此方法返回分页页面*/
      @RequestMapping("doPageUI")
      public String doPageUI(){
    	  return "common/page";
      }
      @RequestMapping("doLoginUI")
      public String doLoginUI(){
    	  return "login";
      }
      @RequestMapping("doLogout")
      public String doLoginout() {
    	  return "login";
      }
}
//http://localhost/CGB-JT-SYS-V1.01/doIndexUI.do



