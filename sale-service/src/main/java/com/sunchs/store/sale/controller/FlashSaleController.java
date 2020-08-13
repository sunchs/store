package com.sunchs.store.sale.controller;

import com.sunchs.store.framework.bean.RequestData;
import com.sunchs.store.framework.bean.ResultData;
import com.sunchs.store.framework.controller.BaseController;
import com.sunchs.store.sale.bean.FlashSaleParam;
import com.sunchs.store.sale.service.impl.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flashSale")
public class FlashSaleController extends BaseController {

    @Autowired
    private FlashSaleService flashSaleService;

    /**
     * 抢购商品
     */
    @PostMapping("/buy")
    public ResultData getPageList(@RequestBody RequestData data) {
        FlashSaleParam param = data.toObject(FlashSaleParam.class);

        flashSaleService.buy(param);

        return success();
    }

}