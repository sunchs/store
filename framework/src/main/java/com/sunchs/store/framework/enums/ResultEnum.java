package com.sunchs.store.framework.enums;

public enum  ResultEnum {

    Failure(0, "操作失败"),
    Success(1, "操作成功"),
    Lost(2, "登录验证失败");

    public Integer status;
    public String message;

    ResultEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
