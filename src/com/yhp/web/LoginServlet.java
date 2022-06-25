package com.yhp.web;

import com.yhp.bean.Users;
import com.yhp.service.UsersService;
import com.yhp.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.调取service
        UsersService usersService = new UsersServiceImpl();
        Users users = usersService.login(username, password);
        //3.跳转页面
        if(users==null){
            //以弹窗方式提示用户，登录失败
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>location.href='login.jsp';alert('用户名或密码不正确');</script>");
        }else{
            //跳转到主页面
            //保存用户信息
            req.getSession().setAttribute("u1",users);
            resp.sendRedirect("index.jsp");
        }
    }
}
