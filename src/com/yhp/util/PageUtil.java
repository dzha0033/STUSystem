package com.yhp.util;

import java.util.List;

public class PageUtil {

    //1.页码值 2.显示条数 3.查询结果列表 4，总条数  5.总页数
    private Integer pageIndex;
    private Integer pageSize=5;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return total%pageSize>0?total/pageSize+1:total/pageSize;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    private List dataList;
    private Integer total;
    private Integer totalPages;
}
