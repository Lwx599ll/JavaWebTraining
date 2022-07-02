package com.sdau.personal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdau.personal.pojo.User;

// /*代表拦截所有
@WebFilter(filterName = "login", urlPatterns = "/*")
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("LoginFilter.doFilter()");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String servletPath = httpServletRequest.getServletPath();
        System.out.println("servletPath: " + servletPath);
        String method = httpServletRequest.getParameter("method");

        // 这些操作是要去完成登录，不需要执行后面是否已经登录的验证
        if (servletPath.endsWith(".jpg")
                || servletPath.endsWith(".png")
                || servletPath.endsWith(".js")
                || servletPath.endsWith(".css")
                || servletPath.equals("/login.jsp")
                || servletPath.equals("/auth")
                || servletPath.equals("/user") && method.equals("login")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
            return;
        }

        // 如果不加这句话，Filter拦截所有的请求就停下来了
        // 加了这句话代表放行，继续往后执行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter.destroy()");
    }

}