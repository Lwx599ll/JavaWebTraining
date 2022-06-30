package com.sdau.personal.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.sdau.personal.util.JSONUtil;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxServlet.doGet()");
		String name = req.getParameter("name");
		System.out.println(name);
		// {}  []
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("age", 23);
		map.put("gender", "男");
		// {'age':23, 'gender':'男'}
		JSONUtil.obj2Json(map, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxServlet.doPost()");
		String name = req.getParameter("name");
		System.out.println(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("age", 24);
		map.put("gender", "女");
		// {'age':23, 'gender':'男'}
		JSONUtil.obj2Json(map, resp);
	}

}
