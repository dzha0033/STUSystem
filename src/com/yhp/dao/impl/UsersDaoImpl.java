package com.yhp.dao.impl;

import com.yhp.bean.Role;
import com.yhp.bean.Users;
import com.yhp.dao.DBUtils;
import com.yhp.dao.UsersDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl extends DBUtils implements UsersDao {
    @Override
    public Users login(String username, String password) {
        Users users = null;
        try {
            String sql = "select * from users where loginname=? and password=?";
            ArrayList arrayList = new ArrayList();
            arrayList.add(username);
            arrayList.add(password);
            resultSet = query(sql, arrayList);
            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                users = new Users();
                users.setLoginName(username);
                users.setRealName(resultSet.getString("realname"));
                users.setUserId(resultSet.getInt("userid"));
                users.setRoleId(resultSet.getInt("roleid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }


        return users;
    }

    @Override
    public List<Users> getUsersList(int pageIndex, int pageSize) {
        String sql = "select userid,loginname,realname,rolename from users u, role r where u.roleid=r.roleid limit ?,?";
        List params = new ArrayList<>();
        List usersList = new ArrayList<>();
        try {
            params.add((pageIndex - 1) * pageSize);
            params.add(pageSize);
            resultSet = query(sql, params);
            while (resultSet.next()) {
                Users users = new Users();
                Role role = new Role();
                users.setUserId(resultSet.getInt("userid"));
                users.setLoginName(resultSet.getString("loginname"));
                users.setRealName(resultSet.getString("realname"));

                role.setRoleName(resultSet.getString("rolename"));
                users.setRole(role);

                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return usersList;
    }

    @Override
    public int total() {
        int total = 0;
        String sql = "select count(1) from users u, role r where u.roleid=r.roleid";
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

    @Override
    public List<Role> getAllRole() {
        String sql = "select * from role;";
        List all = new ArrayList<Role>();
        try {
            resultSet = query(sql, null);
            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                all.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return all;
    }

    @Override
    public int insertUser(Users users) {
        int i = 0;
        try {
            String sql = "insert into users values(null,?,?,?,?,?,?,?,?,?,?)";
            List params = new ArrayList<>();
            params.add(users.getLoginName());
            params.add(users.getPassWord());
            params.add(users.getRealName());
            params.add(users.getSex());
            params.add(users.getEmail());
            params.add(users.getAddress());
            params.add(users.getPhone());
            params.add(users.getCardId());
            params.add(users.getDesc());
            params.add(users.getRoleId());
            i = update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return i;
    }
}
