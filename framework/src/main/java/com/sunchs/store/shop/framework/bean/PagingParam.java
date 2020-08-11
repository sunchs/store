package com.sunchs.store.shop.framework.bean;

public class PagingParam {

    /**
     * 当前页数
     */
    private int pageNow;

    /**
     * 每页条数
     */
    private int pageSize;


    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
