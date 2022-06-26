package com.yhp.dao.impl;

import com.yhp.dao.DBUtils;
import com.yhp.dao.MiddleDao;

import java.sql.SQLException;

public class MIddleDaoImpl extends DBUtils implements MiddleDao {
    @Override
    public int insertMiddle(int key,String[] ids) {
        int k = 0;
        try {
            String sql = "insert into middle values(null,?,?)";
            pps = getPps(sql);
            for(String id:ids){
                pps.setInt(1,key);
                pps.setString(2,id);
                pps.addBatch();
            }
            pps.executeBatch();
            k=1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return k;
    }
}
