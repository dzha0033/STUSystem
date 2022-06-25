package com.yhp.dao;

import com.yhp.bean.Role;
import com.yhp.bean.Users;

import java.util.List;

public interface UsersDao {
    /**
     * 登录方法
     */
    public Users login(String username, String password);

    public List<Users> getUsersList(int pageIndex, int pageSize);

    public int total();

    public List<Role> getAllRole();

    public int insertUser(Users users);
}
