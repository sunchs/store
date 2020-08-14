package com.sunchs.store.sale.service;

import com.sunchs.store.framework.bean.FlashSaleQueueBean;
import com.sunchs.store.sale.bean.FlashSaleParam;

public interface IFlashSaleService {

    /**
     * 新增、编辑 秒杀活动信息
     */
    void save(FlashSaleParam param);

    /**
     * 抢购
     */
    Integer buy(FlashSaleQueueBean bean);
}
