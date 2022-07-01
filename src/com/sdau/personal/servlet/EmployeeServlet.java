package com.sdau.personal.servlet;

import com.sdau.personal.pojo.EmpCountVO;
import com.sdau.personal.pojo.Employee;
import com.sdau.personal.service.IEmployeeService;
import com.sdau.personal.service.impl.EmployeeServiceImpl;
import com.sdau.personal.util.JDBCUtil;
import com.sdau.personal.util.JSONResult;
import com.sdau.personal.util.JSONUtil;
import com.sdau.personal.util.LayUITableResult;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet{
    private IEmployeeService employeeService = new EmployeeServiceImpl();

    // Ctrl+D：删除光标所在的行
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // syst
        // System.out.println("EmployeeServlet.service()");
        // http://localhost:8080/Personal/employee?method=selectAll
        // http://localhost:8080/Personal/employee?method=deleteById&id=2
        String method = req.getParameter("method");
        if (method == null || "".equals(method)) {
            method = "selectAll";
        }
        switch (method) {
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "selectAll":
                selectAll(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "deleteAll":
                deleteAll(req, resp);
                break;
            case "getEmployeeAddPage":
                getEmployeeAddPage(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "getEmployeeUpdatePage":
                getEmployeeUpdatePage(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "selectEmpCount":
                selectEmpCount(req,resp);
                break;
            default:
                break;
        }

    }

    private void selectEmpCount(HttpServletRequest req, HttpServletResponse resp) {
        List<EmpCountVO> list = employeeService.selectEmpCount();

        JSONUtil.obj2Json(JSONResult.ok(list), resp);
    }


    private void getEmployeeAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/employee_add.jsp").forward(req, resp);
    }


    private void update(HttpServletRequest req, HttpServletResponse resp) {
        int id  = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        int basic = Integer.parseInt(req.getParameter("basic"));
        int jiangjin = Integer.parseInt(req.getParameter("jiangjin"));
        int baoxiao = Integer.parseInt(req.getParameter("baoxiao"));
        Employee employee = new Employee(id, name, age, gender, basic,jiangjin,baoxiao);
        employeeService.update(employee);

        JSONUtil.obj2Json(JSONResult.ok("修改成功"), resp);
    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
        // "34, 45"
        String ids = req.getParameter("ids");
        String[] array = ids.split(",");
        employeeService.deleteAll(array);

        JSONUtil.obj2Json(JSONResult.ok("删除成功"), resp);
    }

    private void getEmployeeUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.selectById(id);
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("/employee_update.jsp").forward(req, resp);
    }

    // /employee/selectByPage?page=1&limit=10
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("EmployeeServlet.selectByPage()");
        int page = Integer.parseInt(req.getParameter("page"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));
        LayUITableResult layUITableResult = employeeService.selectByPage(page, limit);
        System.out.println(layUITableResult);

        JSONUtil.obj2Json(layUITableResult, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        int basic = Integer.parseInt(req.getParameter("basic"));
        int jiangjin = Integer.parseInt(req.getParameter("jiangjin"));
        int baoxiao = Integer.parseInt(req.getParameter("baoxiao"));
        Employee employee = new Employee(name, age, gender, basic,jiangjin,baoxiao);
        employeeService.add(employee);

        JSONUtil.obj2Json(JSONResult.ok("添加成功"), resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        employeeService.deleteById(Integer.parseInt(id));
        // 删除完了之后，重定向到查找所有界面
        // /Personal/employee
        // resp.sendRedirect(req.getContextPath() + "/employee?method=selectAll");

        // JSONResult jsonResult = new JSONResult(JSONResult.OK, "删除成功");
        // JSONUtil.array2Json(jsonResult, resp);
        JSONUtil.obj2Json(JSONResult.ok("删除成功"), resp);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> list = employeeService.selectAll();
        // 转发
        // 把list数据放到req内存里面
        req.setAttribute("list", list);
        // 转发到employee_list.jsp展示数据
        req.getRequestDispatcher("/employee_list.jsp").forward(req, resp);
    }




}