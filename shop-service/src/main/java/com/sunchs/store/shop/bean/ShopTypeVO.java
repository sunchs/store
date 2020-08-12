package com.sunchs.store.shop.bean;

public class ShopTypeVO {

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
}
