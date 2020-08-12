package com.sunchs.store.shop.bean;

import com.sunchs.store.shop.exception.ShopException;

import java.util.Objects;

public class ShopTypeParam {

    /**
     * 分类ID
     */
    private int typeId;

    /**
     * 父ID
     */
    private int parentId;

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

    public void filter() {
        if (Objects.isNull(title) || title.length() == 0) {
            throw new ShopException("商品分类标题不能为空！");
        } else if (title.length() > 30) {
            throw new ShopException("商品分类标题不能超过30个字符！");
        }
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
