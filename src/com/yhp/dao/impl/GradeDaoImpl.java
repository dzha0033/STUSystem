package com.yhp.dao.impl;

import com.yhp.bean.Grade;
import com.yhp.dao.DBUtils;
import com.yhp.dao.GradeDao;
import com.yhp.dao.UsersDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends DBUtils implements GradeDao {

    @Override
    public List<Grade> getList() {
        List gs=new ArrayList();
        try {
            String sql="select * from grade";
            resultSet  = query(sql, null);
            while(resultSet.next()){
                Grade grade = new Grade();
                grade.setGradeId(resultSet.getInt("gradeid"));
                grade.setGradeName(resultSet.getString("gradename"));
                gs.add(grade);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return gs;
    }
}
