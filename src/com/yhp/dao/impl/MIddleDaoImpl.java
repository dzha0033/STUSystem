package com.yhp.dao.impl;

import com.yhp.bean.Menu;
import com.yhp.dao.DBUtils;
import com.yhp.dao.MiddleDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MIddleDaoImpl extends DBUtils implements MiddleDao {
    @Override
    public int insertMiddle(int key, String[] ids) {
        int k = 0;
        try {
            String sql = "insert into middle values(null,?,?)";
            pps = getPps(sql);
            for (String id : ids) {
                pps.setInt(1, key);
                pps.setString(2, id);
                pps.addBatch();
            }
            pps.executeBatch();
            k = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return k;
    }

    @Override
    public List<Menu> findById(int key) {
        List menuList = new ArrayList<>();
        try {
            String sql = "select menuid from middle where roleid = ?";
            List params  = new ArrayList();
            params.add(key);
            resultSet = query(sql,params);
            while (resultSet.next()){
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return menuList;
    }

    @Override
    public int deleteMiddle(int key) {
        int k = 0;
        try {
            String sql = "delete from middle where roleid = ?";
            List params = new ArrayList<>();
            params.add(key);
            k = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return k;
    }
}
