package com.yhp.web;

import com.yhp.bean.Role;
import com.yhp.service.RoleService;
import com.yhp.service.impl.RoleServiceImpl;
import com.yhp.service.impl.UsersServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/power/role/roles"})
public class RoleServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("select")) {
            select(req, resp);
        } /*else if (method.equals("add")) {
            add(req, resp);
        } else if (method.equals("save")) {
            save(req, resp);
        }*/
    }

    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
        int pageIndex = (index == null || index.length() == 0) ? 1 : Integer.parseInt(index);

        PageUtil pageUtil = new PageUtil();
        List<Role> usersList = roleService.getAllRole(pageIndex, pageUtil.getPageSize());
        int total = roleService.total();
        pageUtil.setTotal(total);
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setDataList(usersList);

        req.setAttribute("pi", pageUtil);
        req.getRequestDispatcher("list.jsp").forward(req, resp);

    }

    /*protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> all = usersService.getAllRole();
        req.setAttribute("all", all);
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }*/

    /*protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users = new Users();
        users.setLoginName(req.getParameter("loginname"));
        users.setPassWord(req.getParameter("password"));
        users.setRealName(req.getParameter("realname"));
        users.setSex(Integer.parseInt(req.getParameter("sex")));
        users.setEmail(req.getParameter("email"));
        users.setAddress(req.getParameter("address"));
        users.setPhone(req.getParameter("phone"));
        users.setCardId(req.getParameter("cardid"));
        users.setDesc(req.getParameter("desc"));
        users.setRoleId(Integer.parseInt(req.getParameter("roleid")));

        int i = usersService.insertUser(users);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('新增成功');location.href='/power/user/users?method=select'</script>");
        }else{
            writer.println("<script>alert('新增失败');location.href='power/user/users?method=add'</script>");
        }

    }*/

}
