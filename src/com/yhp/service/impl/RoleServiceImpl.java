package com.yhp.service.impl;

import com.yhp.bean.Role;
import com.yhp.dao.RoleDao;
import com.yhp.dao.impl.RoleDaoImpl;
import com.yhp.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();
    @Override
    public List<Role> getAllRole(int pageIndex, int pageSize) {
        return roleDao.getAllRole( pageIndex,pageSize);
    }

    @Override
    public int total() {
        return roleDao.total();
    }
}
