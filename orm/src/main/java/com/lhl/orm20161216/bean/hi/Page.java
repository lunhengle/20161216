package com.lhl.orm20161216.bean.hi;

import java.util.List;

/**
 * Created by lunhengle on 2017/1/4.
 */
public class Page<T> {
    /**
     * 每页多少条默认值.
     */
    private static int PAGE_SIZE_DEFAULT = 4;
    /**
     * 当前多少页.
     */
    private int currentPage;
    /**
     * 总记录数.
     */
    private int totalCount;
    /**
     * 总页数.
     */
    private int totalPage;
    /**
     * 每页记录数.
     */
    private int pageSize;
    /**
     * 数据集合.
     */
    private List<T> list;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getPageSize() {
        return pageSize == 0 ? PAGE_SIZE_DEFAULT : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
