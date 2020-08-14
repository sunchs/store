package com.sunchs.store.sale.bean;

public class FlashSaleShopParam {

    /**
     * 商品ID
     */
    private int shopId;

    /**
     * 出售金额
     */
    private double price;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
