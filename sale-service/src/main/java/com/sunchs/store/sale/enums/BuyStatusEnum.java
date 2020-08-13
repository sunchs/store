package com.sunchs.store.sale.enums;

public enum BuyStatusEnum {

    /**
     *
     */
    Failure(0, "操作失败"),
    Success(1, "操作成功"),
    Lost(2, "登录验证失败"),


    FlashSale(10, "商品库存不足")

    ;


    public int value;
    public String title;

    BuyStatusEnum(int value, String title) {
        this.value = value;
        this.title = title;
    }
}
