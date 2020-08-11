package com.sunchs.store.framework.bean;

import com.sunchs.store.framework.enums.ResultEnum;

public class ResultData {

    public Integer status;

    public String msg;

    public Object data;

    public static ResultData getSuccess() {
        ResultData resultData = new ResultData();
        resultData.status = ResultEnum.Success.getStatus();
        resultData.msg = ResultEnum.Success.getMessage();
        return resultData;
    }

    public static ResultData getSuccess(Object data) {
        ResultData resultData = getSuccess();
        resultData.data = data;
        return resultData;
    }

    public static ResultData getFailure() {
        ResultData resultData = new ResultData();
        resultData.status = ResultEnum.Failure.getStatus();
        resultData.msg = ResultEnum.Failure.getMessage();
        return resultData;
    }

    public static ResultData getFailure(String msg) {
        ResultData resultData = new ResultData();
        resultData.status = ResultEnum.Failure.getStatus();
        resultData.msg = msg;
        return resultData;
    }

    public static ResultData getLoginFailure() {
        ResultData resultData = new ResultData();
        resultData.status = -2;
        resultData.msg = "Token已过期";
        return resultData;
    }
}
