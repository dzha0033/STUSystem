package com.yhp.dao;

import com.yhp.bean.Menu;

import java.util.List;

public interface  MiddleDao {
    int insertMiddle(int key,String[] ids);

    List<Menu> findById(int key);

    int deleteMiddle(int key);
}
