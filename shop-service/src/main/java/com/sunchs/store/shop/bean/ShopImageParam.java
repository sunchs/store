package com.sunchs.store.shop.bean;

public class ShopImageParam {

    /**
     * 图片类型，0、原图；1、大图；2、缩略图 ...
     */
    private int type;

    /**
     * 图片路径
     */
    private String path;

    /**
     * 图片排序
     */
    private int sort;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
