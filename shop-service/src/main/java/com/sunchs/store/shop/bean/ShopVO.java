package com.sunchs.store.shop.bean;

import java.util.List;

public class ShopVO {

    /**
     * 商品ID
     */
    private Integer shopId;

    /**
     * 商品分类ID
     */
    private Integer typeId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品系列号
     */
    private String shopSn;

    /**
     * 详细内容
     */
    private String content;

    /**
     * 商品扩展信息
     */
    List<ShopExtendVO> extendList;

    /**
     * 商品图片信息
     */
    List<ShopImageVO> imageList;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ShopExtendVO> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<ShopExtendVO> extendList) {
        this.extendList = extendList;
    }

    public List<ShopImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShopImageVO> imageList) {
        this.imageList = imageList;
    }
}
