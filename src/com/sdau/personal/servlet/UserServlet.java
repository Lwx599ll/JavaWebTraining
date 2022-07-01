package com.sdau.personal.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdau.personal.pojo.User;
import com.sdau.personal.service.IUserService;
import com.sdau.personal.service.impl.UserServiceImpl;
import com.sdau.personal.util.JSONResult;
import com.sdau.personal.util.JSONUtil;
import com.sdau.personal.util.LayUITableResult;


// http://localhost:8080/Personal/user
// 127.0.0.1:8080/Personal/user
@WebServlet("/user")
public class UserServlet extends HttpServlet{

    private IUserService userService = new UserServiceImpl();

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Ctrl+D：删除光标所在的行
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        System.out.println("UserServlet.service()");
        String method = req.getParameter("method");
        if(method==null||"".equals(method)) {
            method="selectAll";
        }
        switch (method) {

            case "login":
                login(req,resp);
                break;
            case "deleteAll":
                deleteAll(req,resp);
                break;

            case "selectByPage":
                selectByPage(req,resp);
                break;

            case "selectAll":
                selectAll(req, resp);
                break;

            case "deleteById":
                deleteById(req, resp);
                break;

            case "add":
                add(req, resp);
                break;

            case "getUserUpdatePage":
                getUserUpdatePage(req,resp);
                break;

            case "update":
                update(req,resp);
                break;

            case "logout":
                logout(req,resp);
                break;

            default:
                break;
        }

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException 	 {
        // TODO Auto-generated method stub
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String code = req.getParameter("code");

        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");
        if (code == null || "".equals(code) || !codeInSession.equalsIgnoreCase(code)) {
            JSONUtil.obj2Json(JSONResult.error("验证码为空或错误"), resp);
            return;
        }

        User user = userService.login(name, password);
        if (user != null) {
            System.out.println(user);
            session.setAttribute("user", user);
            JSONUtil.obj2Json(JSONResult.ok("登录成功"), resp);
        } else {
            JSONUtil.obj2Json(JSONResult.error("用户名密码错误"), resp);
        }
    }

    private void getUserUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Integer id = Integer.parseInt(req.getParameter("id"));
        User user = userService.selectById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/user_update.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        int id  = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String password = req.getParameter("password");
        Integer type = Integer.parseInt(req.getParameter("type"));
        User user = new User(id, name, age, gender, password, type);
        userService.update(user);
        JSONUtil.obj2Json(JSONResult.ok("修改成功"), resp);

    }

    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        String ids = req.getParameter("ids");
        String[] array = ids.split(",");
        userService.deleteAll(array);

        JSONUtil.obj2Json(JSONResult.ok("删除成功"), resp);
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.selectByPage()");
        Integer page = Integer.parseInt(req.getParameter("page"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));
        LayUITableResult layUITableResult = userService.selectByPage(page,limit);
        System.out.println(layUITableResult);
        JSONUtil.obj2Json(layUITableResult, resp);

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String password = req.getParameter("password");
        Integer type = Integer.parseInt(req.getParameter("type"));
        User user = new User(name, age, gender, password, type);
        userService.add(user);

        JSONUtil.obj2Json(JSONResult.ok("添加成功"), resp);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = userService.selectAll();
        for (User user : list) {
            // sout
            System.out.println(user);
        }

        // 把list数据放到req内存里面
        req.setAttribute("list", list);
        // 转发到user_list.jsp展示数据
        req.getRequestDispatcher("/user_list.jsp").forward(req, resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String id = req.getParameter("id");
        userService.deleteById(Integer.parseInt(id));
        JSONUtil.obj2Json(JSONResult.ok("删除成功"), resp);
    }



}