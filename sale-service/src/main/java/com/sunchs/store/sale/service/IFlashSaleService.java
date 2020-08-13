package com.sunchs.store.sale.service;

import com.sunchs.store.sale.bean.FlashSaleParam;

public interface IFlashSaleService {

    /**
     * 抢购
     */
    Integer buy(FlashSaleParam param);
}
