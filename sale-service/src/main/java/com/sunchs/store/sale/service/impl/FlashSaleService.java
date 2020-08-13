package com.sunchs.store.sale.service.impl;

import com.sunchs.store.db.business.entity.FlashSale;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.sale.bean.FlashSaleParam;
import com.sunchs.store.sale.enums.BuyStatusEnum;
import com.sunchs.store.sale.service.IFlashSaleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Override
    public Integer buy(FlashSaleParam param) {
        // 检查活动状态
        boolean openFlashSale = isOpenFlashSale(param.getShopId());
        if ( ! openFlashSale) {
            return BuyStatusEnum.FlashSale.value;
        }
        // 检查库存是否充足
        int shopStock = getShopStock(param.getShopId());
        if (shopStock <= 0) {
            return BuyStatusEnum.FlashSale.value;
        }
        // 记录抢购等待状态

        // 放入消息队列

        // 响应"等待抢购状态"给用户
        return BuyStatusEnum.Success.value;
    }

    /**
     * 检查活动状态
     */
    private boolean isOpenFlashSale(Integer shopId) {
        FlashSale flashSale = RedisClient.getValue(CacheKeys.SHOP_FLASH_SALE_TIME + shopId, FlashSale.class);
        if (Objects.isNull(flashSale)) {
            return false;
        }
        Date time = new Date(System.currentTimeMillis());
        if (Objects.equals(flashSale.getStatus(), 1) && time.after(flashSale.getStartTime()) && time.before(flashSale.getEndTime())) {
            return true;
        }
        return false;
    }

    /**
     * 获取商品库存
     */
    private int getShopStock(Integer shopId) {
        Integer stock = RedisClient.getValue(CacheKeys.SHOP_STOCK_CACHE_KEY + shopId, Integer.class);
        if (Objects.isNull(stock)) {
            return 0;
        }
        return stock.intValue();
    }
}
