package com.yhp.dao;

import com.yhp.bean.Grade;

import java.util.List;

public interface GradeDao {
    /**
     * 查询年级列表
     */
    public List<Grade> getList();
}
