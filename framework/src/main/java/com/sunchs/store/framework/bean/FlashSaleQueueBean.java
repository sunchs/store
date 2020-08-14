package com.sunchs.store.framework.bean;

public class FlashSaleQueueBean {

    /**
     * 商品ID
     */
    private Integer shopId;

    /**
     * 用户ID
     */
    private Integer userId;

    public static FlashSaleQueueBean getInstance(Integer shopId, Integer userId) {
        FlashSaleQueueBean bean = new FlashSaleQueueBean();
        bean.setShopId(shopId);
        bean.setUserId(userId);
        return bean;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
