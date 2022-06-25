package com.yhp.dao.impl;

import com.yhp.bean.Student;
import com.yhp.dao.DBUtils;
import com.yhp.dao.StudentDao;
import com.yhp.dao.UsersDao;
import com.yhp.util.StudentEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl extends DBUtils implements StudentDao {
    @Override
    public int total(String name, String stuno, int sex) {
            int total=0;
        try {
            List params=new ArrayList();
            StringBuffer sqlbuf=new StringBuffer(" select count(*) from student where 1=1  and state!=4  ");
            if(name!=null&&name.length()>0){
                sqlbuf.append(" and stuname like ? ");
                params.add("%"+name+"%");
            }
            if(stuno!=null&&stuno.length()>0){
                sqlbuf.append(" and stuno=? ");
                params.add(stuno);
            }
            if(sex!=-1){
                sqlbuf.append(" and sex=? ");
                params.add(sex);
            }
            resultSet = query(sqlbuf.toString(), params);
            while(resultSet.next()){
                total=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return total;
    }

    @Override
    public List<Student> getStudents(String name,String stuno,int sex,int pageIndex,int pageSize) {
        List list= new ArrayList<Student>();
        List params=new ArrayList();
        try {
            StringBuffer sqlbuf=new StringBuffer(" select * from student where 1=1 and state!=4  ");
            if(name!=null&&name.length()>0){
                sqlbuf.append(" and stuname like ? ");
                params.add("%"+name+"%");
            }
            if(stuno!=null&&stuno.length()>0){
                sqlbuf.append(" and stuno=? ");
                params.add(stuno);
            }
            if(sex!=-1){
                sqlbuf.append(" and sex=? ");
                params.add(sex);
            }
            //分页
            sqlbuf.append(" limit ?,?");  //1 5   limit 0,5
            // limit  (pageIndex-1)*pageSize,pageSize;
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);


            resultSet = query(sqlbuf.toString(), params);
            while(resultSet.next()){
                Student student = new Student();
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setStuName(resultSet.getString("stuname"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setProfession(resultSet.getString("profession"));
                student.setRegDate(resultSet.getDate("regdate"));
                //补全所有的列
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }



    @Override
    public int insertStu(Student student) {
        int i =0;
        try {
            String sql="insert into student values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            List params=new ArrayList();
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(new Date());
            params.add(StudentEnum.READING.type);
            params.add(student.getIntrodction());
            params.add(student.getGid());
            i = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return i;
    }


    @Override
    public Student findById(int sid) {
        Student student = new Student();
        try {
            String sql="select * from student where stuid=?";
            List params=new ArrayList();
            params.add(sid);

            resultSet=query(sql,params);
            while(resultSet.next()){
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setStuName(resultSet.getString("stuname"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setProfession(resultSet.getString("profession"));
                student.setAddress(resultSet.getString("address"));
                student.setRegDate(resultSet.getDate("regdate"));
                student.setEmail(resultSet.getString("email"));
                student.setIntrodction(resultSet.getString("introduction"));
                student.setGid(resultSet.getInt("gid"));
                student.setRegistered(resultSet.getString("registered"));
                student.setIdNumber(resultSet.getString("idnumber"));
                student.setPolitics(resultSet.getString("politics"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return student;
    }


    @Override
    public int updateStu(Student student) {
        int update = 0;
        try {
            //这里需要大家自己完善非主键列以外的列的更新
            String sql="update student set stuname=?,address=?,sex=? where stuid=?";
            List params=new ArrayList();
            params.add(student.getStuName());
            params.add(student.getAddress());
            params.add(student.getSex());
            params.add(student.getStuId());

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return update;
    }


    @Override
    public int delStu(String sid) {
        int update = 0;
        try {
            String sql="update student  set state=? where stuid=?";
            List params=new ArrayList();
            params.add(StudentEnum.DELETE.type);
            params.add(sid);
            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return update;
    }
}
