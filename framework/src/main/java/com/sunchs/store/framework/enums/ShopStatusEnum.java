package com.sunchs.store.framework.enums;

public enum ShopStatusEnum {

    /**
     * 停售
     */
    DISABLE(0, "停用"),

    /**
     * 开售
     */
    ENABLE(1, "启用"),

    /**
     * 删除
     */
    DELETE(2, "删除");

    public int value;
    public String valueName;

    ShopStatusEnum(int value, String valueName) {
        this.value = value;
        this.valueName = valueName;
    }
}
