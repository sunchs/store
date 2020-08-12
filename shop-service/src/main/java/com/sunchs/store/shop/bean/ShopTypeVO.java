package com.sunchs.store.shop.bean;

import java.util.List;

public class ShopTypeVO {

    /**
     * 分类ID
     */
    private int typeId;

    /**
     * 分类标题
     */
    private String title;

    /**
     * 子分类
     */
    private List<ShopTypeVO> children;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ShopTypeVO> getChildren() {
        return children;
    }

    public void setChildren(List<ShopTypeVO> children) {
        this.children = children;
    }
}
