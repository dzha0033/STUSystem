package com.yhp.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginout")
public class OutServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //清除session
        req.getSession().invalidate();
        //跳转页面
        //resp.sendRedirect("/login.jsp");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("<script>alert('退出成功');top.location.href='login.jsp';</script>");
    }
}

