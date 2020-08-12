package com.sunchs.store.shop.bean;

import java.math.BigDecimal;

public class ShopExtendParam {

    /**
     * 扩展ID
     */
    private int extId;

    /**
     * 颜色ID
     */
    private int colorId;

    /**
     * 尺寸ID
     */
    private int sizeId;

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
    private int weight;

    /**
     * 库存
     */
    private int stock;

    /**
     * 库存警告
     */
    private int stockWarning;

    public int getExtId() {
        return extId;
    }

    public void setExtId(int extId) {
        this.extId = extId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockWarning() {
        return stockWarning;
    }

    public void setStockWarning(int stockWarning) {
        this.stockWarning = stockWarning;
    }
}
