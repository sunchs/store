package com.sunchs.store.sale.bean;

import java.util.List;

public class FlashSaleParam {

    /**
     * 促销ID
     */
    private int saleId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 状态，0、停用；1、启用；2、删除
     */
    private int status;

    /**
     * 产品列表
     */
    private List<FlashSaleShopParam> shopList;

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<FlashSaleShopParam> getShopList() {
        return shopList;
    }

    public void setShopList(List<FlashSaleShopParam> shopList) {
        this.shopList = shopList;
    }
}
