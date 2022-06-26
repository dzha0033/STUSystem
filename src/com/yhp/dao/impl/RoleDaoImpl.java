package com.yhp.dao.impl;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.dao.DBUtils;
import com.yhp.dao.RoleDao;

import java.sql.ResultSet;
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

    @Override
    public List<Menu> getAllMenu() {
        String sql = "select * from menu";
        List all = new ArrayList<Menu>();
        try {
            resultSet = query(sql, null);
            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUpMenuId(resultSet.getInt("upmenuid"));
                menu.setState(resultSet.getInt("state"));
                menu.setDesc(resultSet.getString("desc"));
                menu.setUrl(resultSet.getString("url"));
                all.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return all;
    }

    @Override
    public int insertRol(Role role) {
        int key = 0;
        try {
            String sql = "insert into role values(?,?,?)";
            List params = new ArrayList<>();
            params.add(role.getRoleId());
            params.add(role.getRoleName());
            params.add(role.getRoleState());
            int i = update(sql, params);

            ResultSet generatedKeys = pps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return key;
    }

    @Override
    public Role findById(int id) {
        Role role = new Role();
        try {
            String sql = "select * from role where roleid = ?";
            List params = new ArrayList<>();
            params.add(id);

            resultSet = query(sql, params);
            while (resultSet.next()){
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return role;
    }

    @Override
    public int updateRole(Role role) {
        int key = 0;
        try {
            String sql = "update role set rolename=?,rolestate=? where roleid=?";
            List params = new ArrayList();
            params.add(role.getRoleName());
            params.add(role.getRoleState());
            params.add(role.getRoleId());
            int k = update(sql,params);
            ResultSet generatedKeys = pps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return key;
    }

    @Override
    public int deleteRole(int id) {
        int k = 0;
        try {
            String sql = "delete from role where roleid = ?";
            List params = new ArrayList<>();
            params.add(id);
            k = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return k;
    }

    @Override
    public Role findAllById(int roleid) {
        Role role = new Role();
        List menuList=new ArrayList();

        String sql = "select * from role r, menu m, middle mid where r.roleid=mid.roleid and mid.menuid=m.menuid and r.roleid=?";
        List params=new ArrayList();
        params.add(roleid);

        try {
            resultSet=query(sql,params);
            while(resultSet.next()){
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));

                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUrl(resultSet.getString("url"));
                menu.setState(resultSet.getInt("state"));
                menu.setUpMenuId(resultSet.getInt("upmenuid"));
                menuList.add(menu);
            }
            role.setMenuList(menuList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return role;
    }


}
