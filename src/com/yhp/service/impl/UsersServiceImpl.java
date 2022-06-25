package com.yhp.service.impl;

import com.yhp.bean.Role;
import com.yhp.bean.Users;
import com.yhp.dao.UsersDao;
import com.yhp.dao.impl.UsersDaoImpl;
import com.yhp.service.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao=new UsersDaoImpl();

    @Override
    public Users login(String username, String password) {
        return usersDao.login(username,password);
    }

    @Override
    public List<Users> getUsersList(int pageIndex, int pageSize) {
        return usersDao.getUsersList(pageIndex,pageSize);
    }

    @Override
    public int total() {
        return usersDao.total();
    }

    @Override
    public List<Role> getAllRole() {
        return usersDao.getAllRole();
    }

    @Override
    public int insertUser(Users users) {
        return usersDao.insertUser(users);
    }
}
