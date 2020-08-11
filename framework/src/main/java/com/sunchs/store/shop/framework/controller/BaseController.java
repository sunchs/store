package com.sunchs.store.shop.framework.controller;

import com.sunchs.store.shop.framework.bean.ResultData;

public class BaseController {

    public ResultData success() {
        return ResultData.getSuccess();
    }

    public ResultData success(Object src) {
        return ResultData.getSuccess(src);
    }



}
