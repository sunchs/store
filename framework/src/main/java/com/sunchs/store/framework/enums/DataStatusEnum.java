package com.sunchs.store.framework.enums;

public enum DataStatusEnum {

    /**
     * 停用
     */
    DISABLE(0, "停用"),

    /**
     * 启用
     */
    ENABLE(1, "启用"),

    /**
     * 删除
     */
    DELETE(2, "删除");

    public int value;
    public String valueName;

    DataStatusEnum(int value, String valueName) {
        this.value = value;
        this.valueName = valueName;
    }
}
