package com.yhp.web;

import com.yhp.bean.Grade;
import com.yhp.bean.Student;
import com.yhp.service.GradeService;
import com.yhp.service.StudentService;
import com.yhp.service.impl.GradeServiceImpl;
import com.yhp.service.impl.StudentServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Educational/student/studentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("insert".equals(method)){
                insert(req,resp);
        }else if("update".equals(method)){
            update(req, resp);
        }else if("findbyid".equals(method)){
            findbyid(req, resp);
        }else if("delete".equals(method)){
            deletestu(req, resp);
        }else{
            findlist(req, resp);
        }
    }

    //删除学员
    protected void deletestu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        //根据主键查询学生信息
        StudentService service=new StudentServiceImpl();
        int i = service.delStu(sid);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('删除成功');location.href='/Educational/student/studentServlet'</script>");
        }else{
            writer.println("<script>alert('删除失败');location.href='/Educational/student/studentServlet'</script>");
        }
    }
    //新增学员
    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuNo = req.getParameter("stuNo");
        String stuname = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idnumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introdction = req.getParameter("introdction");
        //调取service
        StudentService service=new StudentServiceImpl();
        //将参数封装到学生对象中
        Student student = new Student();
        student.setStuNo(stuNo);
        student.setStuName(stuname);
        student.setGid(Integer.parseInt(gid));
        student.setSex(Integer.parseInt(sex));
        student.setEmail(email);
        student.setPhone(phone);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setPolitics(politics);
        student.setIdNumber(idnumber);
        student.setProfession(profession);
        student.setIntrodction(introdction);
        int i = service.insertStu(student);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('新增成功');location.href='/Educational/student/studentServlet'</script>");
        }else{
            writer.println("<script>alert('新增失败');location.href='/Educational/student/getGradeList'</script>");
        }

    }
    //主键查询
    protected void findbyid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sid = req.getParameter("sid");
        //根据主键查询学生信息
        StudentService service=new StudentServiceImpl();
        Student student = service.findById(Integer.parseInt(sid));

        //查询年级列表
        GradeService gradeService=new GradeServiceImpl();
        List<Grade> list = gradeService.getList();

        req.setAttribute("glist",list);
        req.setAttribute("stu",student);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }
    //查询列表
    protected void findlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1.获取参数
        //1.1 模糊查条件
        String stuname = req.getParameter("stuname");
        System.out.println(stuname);
        String stuno = req.getParameter("stuno");
        String sex = req.getParameter("sex");
        //1.2 分页数据   limit 开始位置,显示条数
        //页码值(当前页码值)
        String pageIndex = req.getParameter("pageIndex");
        //如果页面没有传入pageIndex的值，则认为默认查询第一页
        int index=pageIndex==null?1:Integer.parseInt(pageIndex);
        //2.调取service方法
        PageUtil pageUtil = new PageUtil();
        StudentService service=new StudentServiceImpl();
        int usex=(sex==null||sex.length()==0?-1:Integer.parseInt(sex));
        List<Student> students =
                service.getStudents(stuname,stuno,usex,index,pageUtil.getPageSize());
        //获取总页数=总条数%每页显示的条数>0?总条数/每页显示条数+1:总条数/每页显示条数
        int total = service.total(stuname, stuno,usex);//总条数
        pageUtil.setTotal(total);
        //3.跳转页面
        //如果后台想给前台传数据，是一定要在后台存值的
        pageUtil.setDataList(students);
        pageUtil.setPageIndex(index);
        //存储模糊查条件
        req.setAttribute("stuname",stuname);
        req.setAttribute("stuno",stuno);
        req.setAttribute("sex",sex);
        //存储分页数据
        req.setAttribute("p1",pageUtil);
        req.getRequestDispatcher("list.jsp").forward(req,resp);
    }
    //修改学员
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        String stuNo = req.getParameter("stuNo");
        String stuname = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idnumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introdction = req.getParameter("introdction");

        //步骤2
        Student stu = new Student();
        stu.setStuName(stuname);
        stu.setAddress(address);
        stu.setSex(Integer.parseInt(sex));
        stu.setStuId(Integer.parseInt(sid));

        StudentService service=new StudentServiceImpl();
        int i = service.updateStu(stu);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('更新成功');location.href='/Educational/student/studentServlet'</script>");
        }else{
            writer.println("<script>alert('更新失败');location.href='/Educational/student/studentServlet?method=findbyid&sid="+sid+"'</script>");
        }
    }
}
