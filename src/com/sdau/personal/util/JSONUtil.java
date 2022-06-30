package com.sdau.personal.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONUtil {

	/**
	 * 将任意对象转换为json返回给浏览器 {} []
	 * to 
	 * @param obj
	 */
	public static void obj2Json(Object obj, HttpServletResponse response) {
		JsonConfig jsonConfig = new JsonConfig();
		String json = JSONObject.fromObject(obj, jsonConfig).toString();
		response.setContentType("text/json;charset=utf-8");
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将任意集合对象转换为json返回给浏览器
	 * 
	 * @param obj
	 */
	public static void array2Json(Object obj, HttpServletResponse response) {
		JsonConfig jsonConfig = new JsonConfig();
		String json = JSONArray.fromObject(obj, jsonConfig).toString();
		response.setContentType("text/json;charset=utf-8");
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
