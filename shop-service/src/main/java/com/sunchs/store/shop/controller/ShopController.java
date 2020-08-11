package com.sunchs.store.shop.controller;

import com.sunchs.store.framework.bean.RequestData;
import com.sunchs.store.framework.bean.ResultData;
import com.sunchs.store.framework.controller.BaseController;
import com.sunchs.store.shop.bean.ShopParam;
import com.sunchs.store.shop.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    /**
     * 医院信息 添加、编辑
     */
    @PostMapping("/save")
    public ResultData save(@RequestBody RequestData data) {
        ShopParam param = data.toObject(ShopParam.class);
        shopService.save(param);
        return success();
    }

    /**
     * 修改商品状态
     * 0、停售；1、开售；2、删除
     */
    @PostMapping("/updateStatus")
    public ResultData updateStatus(@RequestBody RequestData data) {
        Integer shopId = data.getInt("shopId");
        Integer status = data.getInt("status");
        shopService.updateStatus(shopId, status);
        return success();
    }
}