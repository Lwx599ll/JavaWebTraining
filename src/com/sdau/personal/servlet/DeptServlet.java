package com.sdau.personal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdau.personal.pojo.vo.DeptEmpCountVO;
import com.sdau.personal.service.IDeptService;
import com.sdau.personal.service.impl.DeptServiceImpl;
import com.sdau.personal.util.JSONResult;
import com.sdau.personal.util.JSONUtil;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet{
	private IDeptService deptService = new DeptServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// syst
		// System.out.println("UserServlet.service()");
		// http://localhost:8080/Personal/user?method=selectAll
		// http://localhost:8080/Personal/user?method=deleteById&id=2
		String method = req.getParameter("method");
		if (method == null || "".equals(method)) {
			method = "selectDeptEmpCount";
		}
		switch (method) {
		case "selectDeptEmpCount":
			selectDeptEmpCount(req, resp);
			break;
		default:
			break;
		}
		
	}

	private void selectDeptEmpCount(HttpServletRequest req, HttpServletResponse resp) {
		List<DeptEmpCountVO> list = deptService.selectDeptEmpCount();
		
		JSONUtil.obj2Json(JSONResult.ok(list), resp);
	}

}

