package com.sdau.personal.util;

// JSON格式的通用响应对象，封装的就是后台返回给前台的所有信息
public class JSONResult {
	public static final int ERROR = 1;
	public static final int OK = 0;

	// 当前状态（程序员判断状态）:成功、失败、未登录、没有权限
	// 当前登录是成功还是失败要告诉前台，前台才能知道弹出的提示框用errorMsg、okMsg
	private Integer code;
	// 描述信息（主要是给用户看的提示信息）
	private String msg;
	// 后台返回给前端的数据 Object，User、List<User>
	private Object data;
	
	public boolean isOk() {
		return code == OK;
	}

	public JSONResult() {
	}
	
	public JSONResult(Integer code) {
		this.code = code;
	}

	public JSONResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public JSONResult(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	public JSONResult(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	// 告诉前台成功：code
	public static JSONResult ok() {
		return new JSONResult(OK);
	}
	
	// 告诉前台成功：code、msg
	public static JSONResult ok(String msg) {
		return new JSONResult(OK, msg);
	}
	
	// 告诉前台成功：code、data
	public static JSONResult ok(Object data) {
		return new JSONResult(OK, data);
	}
	
	// 告诉前台成功：code、msg、data
	public static JSONResult ok(String msg, Object data) {
		return new JSONResult(OK, msg, data);
	}
	

	// 告诉前台成功：code
	public static JSONResult error() {
		return new JSONResult(ERROR);
	}
	
	// 告诉前台成功：code、msg
	public static JSONResult error(String msg) {
		return new JSONResult(ERROR, msg);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
