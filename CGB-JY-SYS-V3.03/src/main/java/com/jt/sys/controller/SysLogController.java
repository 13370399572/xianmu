package com.jt.sys.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;
/**Spring MVC中的控制层对象(后端控制器)
 * 思考:如何提供电商系统的性能?(响应的速度)
 * 1)影响系统性能的原因?
 * a)请求数据传输时间?(带宽,数据量,压缩)
 * b)数据处理时间?(架构,CPU,硬盘，内存，线程，池，算法，。。。)
 * c)响应数据传输时间?(带宽,距离,数据量，缓存)
 * d)响应数据渲染时间?(html,CSS)
 * 2)解决问题。。。。
 * */
@Controller
@RequestMapping("/log/")
public class SysLogController {
	  @Autowired
	  private SysLogService sysLogService;
	 
	  @RequestMapping("doLogListUI")
	  public String doLogListUI(){
		  
		  return "sys/log_list"; 
	  }
	  
	  @RequestMapping("doDeleteObjects")
	  @ResponseBody
	  public JsonResult doDeleteObjects(
			  Integer... ids){
		  System.out.println("ids="+Arrays.toString(ids));
		  sysLogService.deleteObjects(ids);
		  return new JsonResult("delete ok");
	  }
	  @RequestMapping("doFindPageObjects")
      @ResponseBody
	  public JsonResult doFindPageObjects(
			  String username,
			  Integer pageCurrent){
    	  //System.out.println("query.username="+username);
    	  //try{Thread.sleep(5000);}
    	 // //catch(Exception e){e.printStackTrace();}
    	  System.out.println("pageCurrent="+pageCurrent);
		  PageObject<SysLog> pageObject=
    	  sysLogService.findPageObject(username,
    			  pageCurrent);
		  System.out.println("sysLogService="+sysLogService.getClass().getName());
		  return new JsonResult(pageObject);
	  }//借助JsonResult对象封装控制层要返回的结果
     
	
}






