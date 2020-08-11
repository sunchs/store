package com.sunchs.store.framework.enums;

public enum UserTypeEnum {

    /**
     * 超级管理员
     */
    ADMIN(1, "超级管理员");

    public int value;
    public String info;

    UserTypeEnum(Integer status, String message) {
        this.value = status;
        this.info = message;
    }

    public int getValue() {
        return value;
    }

    public String getInfo() {
        return info;
    }
}
