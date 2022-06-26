package com.yhp.service.impl;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.bean.Users;
import com.yhp.dao.RoleDao;
import com.yhp.dao.UsersDao;
import com.yhp.dao.impl.RoleDaoImpl;
import com.yhp.dao.impl.UsersDaoImpl;
import com.yhp.service.UsersService;

import java.util.ArrayList;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao = new UsersDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Users login(String username, String password) {
        Users users = usersDao.login(username, password);
        if (users == null) {
            return null;
        }
        int roleId = users.getRoleId();
        Role role = roleDao.findAllById(roleId);
        List<Menu> menuList = role.getMenuList();
        List<Menu> newAll = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getUpMenuId() == 0) {
                List<Menu> secMenu = new ArrayList<>();
                for (Menu secondMenu : menuList) {
                    if (menu.getMenuId() == secondMenu.getUpMenuId()) {
                        secMenu.add(secondMenu);
                    }
                }
                menu.setSecondMenu(secMenu);
                newAll.add(menu);
            }
        }
        role.setMenuList(newAll);
        users.setRole(role);
        return users;
    }

    @Override
    public List<Users> getUsersList(int pageIndex, int pageSize) {
        return usersDao.getUsersList(pageIndex, pageSize);
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
