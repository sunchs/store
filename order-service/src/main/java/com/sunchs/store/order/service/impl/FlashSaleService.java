package com.sunchs.store.order.service.impl;

import com.sunchs.store.framework.bean.FlashSaleQueueBean;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.Logger;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.framework.util.JsonUtil;
import com.sunchs.store.order.service.IFlashSaleService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Override
    public void execQueue(String msg) {
        try {
            FlashSaleQueueBean bean = JsonUtil.toObject(msg, FlashSaleQueueBean.class);
            String stockKey = CacheKeys.SHOP_STOCK + bean.getShopId();
            Integer shopStock = RedisClient.getValue(stockKey, Integer.class);
            if (Objects.nonNull(shopStock) && shopStock.intValue() > 0) {
                /**有库存处理**/
                // 减少商品库存
                RedisClient.decr(stockKey);
                // 下订单

                // 修改响应状态
                updateFlashSaleResponseStatus(1, bean);
            } else {
                /**无库存处理**/
                updateFlashSaleResponseStatus(2, bean);
            }
        } catch (Exception e) {
            Logger.error("处理消息队列异常，秒杀", e);
        }
    }

    /**
     * 修改抢购状态
     */
    private void updateFlashSaleResponseStatus(Integer status, FlashSaleQueueBean bean) {
        String statusKey = CacheKeys.USER_FLASH_SALE_STATUS + bean.getShopId() + ":" + bean.getUserId();
        RedisClient.setValue(statusKey, status + "");
    }
}
