package com.lhl.orm20161216.bean.jdbc;

/**
 * Created by lunhengle on 2016/12/26.
 * 分页
 */
public class Page {
    /**
     * 总记录数.
     */
    private int rowTotal;
    /**
     * 每页记录数.
     */
    private int pageSize = 10;
    /**
     * 当前页码.
     */
    private int count;
    /**
     * 总页数.
     */
    private int total;
    /**
     * 起始记录下标.
     */
    private int beginIndex;
    /**
     * 结束记录下标.
     */
    private int endIndex;

    /**
     * 总记录， 当前页码 构造
     *
     * @param rowTotal 总记录
     * @param count    当前页面
     */
    public Page(int rowTotal, int count) {
        this.rowTotal = rowTotal;
        this.count = count;
        this.calculate();
    }

    /**
     * 总记录，当前页码，每页记录数.
     *
     * @param rowTotal 总记录
     * @param count    当前页
     * @param pageSize 每页记录数
     */
    public Page(int rowTotal, int count, int pageSize) {
        this.rowTotal = rowTotal;
        this.count = count;
        this.pageSize = pageSize;
        this.calculate();
    }

    private void calculate() {
        //计算总页数
        total = rowTotal / pageSize + ((rowTotal % pageSize > 0) ? 1 : 0);
        //当前页数
        if (count > total) {
            count = total;
        } else if (count < 1) {
            count = 1;
        }
        //起始记录下标
        beginIndex = (count - 1) * pageSize;
        //结束记录下标
        endIndex = beginIndex + pageSize;
        if (endIndex > rowTotal) {
            endIndex = rowTotal;
        }

    }

    public int getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(int rowTotal) {
        this.rowTotal = rowTotal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
