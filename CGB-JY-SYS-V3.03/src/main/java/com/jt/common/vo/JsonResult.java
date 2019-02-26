package com.jt.common.vo;

import java.io.Serializable;

public class JsonResult implements Serializable{
	private static final long serialVersionUID = 3094092862970827320L;
	/**响应状态的状态码*/
	private Integer state=1;//ok,
	/**响应消息*/
	private String  message="ok";//message
	/**响应数据(例如查询的结果)*/
	private Object data;
	
	public JsonResult() {
	}
	public JsonResult(String message) {
		this.message=message;
	}
	public JsonResult(Object data){
		this.data=data;
	}
	public JsonResult(Throwable e){
		this.state=0;
		this.message=e.getMessage();
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}