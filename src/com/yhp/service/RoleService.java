package com.yhp.service;

import com.yhp.bean.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRole(int pageIndex, int pageSize);

    public int total();
}
