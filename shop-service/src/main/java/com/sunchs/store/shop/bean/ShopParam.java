package com.sunchs.store.shop.bean;

import com.sunchs.store.framework.bean.PagingParam;

import java.util.List;

public class ShopParam extends PagingParam {

    /**
     * 商品ID
     */
    private int shopId;

    /**
     * 商品分类ID
     */
    private int typeId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品系列号
     */
    private String shopSn;

    /**
     * 商品状态，0、停售；1、开售；2、删除
     */
    private Integer status;

    /**
     * 详细内容
     */
    private String content;

    /**
     * 商品扩展信息
     */
    List<ShopExtendParam> extendList;

    /**
     * 商品图片信息
     */
    List<ShopImageParam> imageList;


    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

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

    public String getShopSn() {
        return shopSn;
    }

    public void setShopSn(String shopSn) {
        this.shopSn = shopSn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ShopExtendParam> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<ShopExtendParam> extendList) {
        this.extendList = extendList;
    }

    public List<ShopImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShopImageParam> imageList) {
        this.imageList = imageList;
    }
}
