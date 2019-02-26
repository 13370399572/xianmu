package com.jt.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;

@ControllerAdvice
public class GlabaException {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
    public JsonResult dpHandleRuntiemException(
    	RuntimeException e	) {
    		e.printStackTrace();
    		return new JsonResult(e);
    		}
}
