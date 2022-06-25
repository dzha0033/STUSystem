package com.yhp.service;

import com.yhp.bean.Student;

import java.util.List;

public interface StudentService {
    /**
     * 获取学员的信息列表
     * pageIndex  页码值
     * pageSize  每页显示条数
     */
    public List<Student> getStudents(String name,String stuno,int sex,int pageIndex,int pageSize);
    /**
     * 获得总条数(基于模糊查询)
     */
    public  int total(String name,String stuno,int sex);
    /**
     * 新增学生
     */
    public int insertStu(Student student);

    /**
     * 主键查询
     */
    public Student findById(int sid);
    /**
     * 修改学生
     */
    public int updateStu(Student student);

    /**
     * 删除学生
     */
    public int delStu(String sid);
}
