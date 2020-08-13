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
     * 市场价格
     */
    private Double marketPrice;

    /**
     * 出售价格
     */
    private Double price;

    /**
     * 重量（单位：克）
     */
    private Integer weight;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存警告
     */
    private Integer stockWarning;

    /**
     * 详细内容
     */
    private String content;

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

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockWarning() {
        return stockWarning;
    }

    public void setStockWarning(Integer stockWarning) {
        this.stockWarning = stockWarning;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ShopImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShopImageVO> imageList) {
        this.imageList = imageList;
    }
}
