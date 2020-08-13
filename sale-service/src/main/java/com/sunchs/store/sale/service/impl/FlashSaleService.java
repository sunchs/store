package com.sunchs.store.sale.service.impl;

import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.sale.bean.FlashSaleParam;
import com.sunchs.store.sale.enums.BuyStatusEnum;
import com.sunchs.store.sale.service.IFlashSaleService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Override
    public Integer buy(FlashSaleParam param) {
//        // 判断活动是否开启
//        CacheKeys.SHOP_FLASH_SALE_TIME + param.getExtId();
//
//
//        // 检查是否有库存
//        String stockKey = CacheKeys.SHOP_STOCK_CACHE_KEY + param.getExtId();
//        Integer stock = RedisClient.getValue(stockKey, Integer.class);
//        if (Objects.isNull(stock) || (stock.intValue() < 1)) {
//            return BuyStatusEnum.FlashSale.value;
//        }



    }
}
