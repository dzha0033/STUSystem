package com.yhp.service.impl;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.dao.MiddleDao;
import com.yhp.dao.RoleDao;
import com.yhp.dao.impl.MIddleDaoImpl;
import com.yhp.dao.impl.RoleDaoImpl;
import com.yhp.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();
    private MiddleDao middleDao = new MIddleDaoImpl();
    @Override
    public List<Role> getAllRole(int pageIndex, int pageSize) {
        return roleDao.getAllRole( pageIndex,pageSize);
    }

    @Override
    public int total() {
        return roleDao.total();
    }

    @Override
    public List<Menu> getAllMenu() {
        return roleDao.getAllMenu();
    }

    @Override
    public int insertRole(String rolename, int state, String[] ids) {
        int k1 = 0;
        try {
            Role role = new Role();
            role.setRoleState(state);
            role.setRoleName(rolename);
            int key = roleDao.insertRol(role);
            middleDao.insertMiddle(key,ids);
            k1 = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k1;
    }


}
