package com.sunchs.store.shop.bean;

public class ShopTypeParam {

    /**
     * 分类ID
     */
    private int typeId;

    /**
     * 父ID
     */
    private int pid;

    /**
     * 分类标题
     */
    private String title;

    /**
     * 分类状态，0、停用；1、启用；2、删除
     */
    private int status;

    /**
     * 排序
     */
    private int sort;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
