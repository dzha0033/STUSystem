package com.yhp.web;

import com.yhp.bean.Grade;
import com.yhp.service.GradeService;
import com.yhp.service.impl.GradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/Educational/student/getGradeList")
public class GradeListServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //查询年级列表的方法
        GradeService service=new GradeServiceImpl();
        List<Grade> list = service.getList();
        req.setAttribute("glist",list);
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }
}
