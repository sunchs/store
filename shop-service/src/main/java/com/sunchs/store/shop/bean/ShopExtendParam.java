package com.sunchs.store.shop.bean;

import java.math.BigDecimal;

public class ShopExtendParam {

    /**
     * 扩展ID
     */
    private Integer extId;

    /**
     * 商品ID
     */
    private Integer shopId;

    /**
     * 颜色ID
     */
    private Integer colorId;

    /**
     * 尺寸ID
     */
    private Integer sizeId;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 出售价格
     */
    private BigDecimal price;

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

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
}
