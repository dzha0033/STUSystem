package com.yhp.service.impl;

import com.yhp.bean.Student;
import com.yhp.dao.StudentDao;
import com.yhp.dao.impl.StudentDaoImpl;
import com.yhp.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao dao=new StudentDaoImpl();
    @Override
    public List<Student> getStudents(String name,String stuno,int sex,int pageIndex,int pageSize) {
        return dao.getStudents(name,stuno,sex,pageIndex,pageSize);
    }

    @Override
    public int total(String name, String stuno, int sex) {
        return dao.total(name, stuno, sex);
    }

    @Override
    public int insertStu(Student student) {
        return dao.insertStu(student);
    }

    @Override
    public Student findById(int sid) {
        return dao.findById(sid);
    }

    @Override
    public int updateStu(Student student) {
        return dao.updateStu(student);
    }

    @Override
    public int delStu(String sid) {
        return dao.delStu(sid);
    }
}
