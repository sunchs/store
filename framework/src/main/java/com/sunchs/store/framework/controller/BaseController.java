package com.sunchs.store.framework.controller;

import com.sunchs.store.framework.bean.ResultData;

public class BaseController {

    public ResultData success() {
        return ResultData.getSuccess();
    }

    public ResultData success(Object src) {
        return ResultData.getSuccess(src);
    }



}
