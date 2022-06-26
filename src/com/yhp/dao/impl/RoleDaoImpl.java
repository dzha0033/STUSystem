package com.yhp.dao.impl;

import com.yhp.bean.Role;
import com.yhp.dao.DBUtils;
import com.yhp.dao.RoleDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends DBUtils implements RoleDao {

    @Override
    public List<Role> getAllRole(int pageIndex, int pageSize) {
        String sql = "select roleid,rolename,rolestate from role limit ?,?";
        List params = new ArrayList<>();
        List roleList = new ArrayList<>();
        try {
            params.add((pageIndex - 1) * pageSize);
            params.add(pageSize);
            resultSet = query(sql, params);
            while (resultSet.next()) {
                Role role = new Role();

                role.setRoleId((resultSet.getInt("roleid")));
                role.setRoleState(resultSet.getInt("rolestate"));
                role.setRoleName(resultSet.getString("rolename"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return roleList;
    }

    @Override
    public int total() {
        int total = 0;
        String sql = "select count(1) from role ";
        try {
            resultSet = query(sql, null);
            while (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return total;
    }
}
