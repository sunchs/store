package com.sunchs.store.order.service.impl;

import com.sunchs.store.db.business.entity.OrderInfo;
import com.sunchs.store.db.business.service.impl.OrderInfoServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopServiceImpl;
import com.sunchs.store.framework.bean.FlashSaleQueueBean;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.Logger;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.framework.lock.DistributedLock;
import com.sunchs.store.framework.lock.LockKey;
import com.sunchs.store.framework.util.JsonUtil;
import com.sunchs.store.order.service.IFlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Autowired
    private ShopServiceImpl shopService;
    @Autowired
    private OrderInfoServiceImpl orderInfoService;

    @Override
    public void execQueue(String msg) {
        try {
            FlashSaleQueueBean bean = JsonUtil.toObject(msg, FlashSaleQueueBean.class);
            DistributedLock.execute(LockKey.SHOP_FLASH_SALE_LOCK + bean.getShopId(), () -> {
                // 获取库存数量
                String stockKey = CacheKeys.SHOP_STOCK + bean.getShopId();
                Integer shopStock = RedisClient.getValue(stockKey, Integer.class);
                if (Objects.nonNull(shopStock) && shopStock.intValue() > 0) {
                    /**有库存处理**/
                    // 减少Redis商品库存
                    RedisClient.decr(stockKey);
                    // 减少Mysql商品库存
                    shopService.decrStock(bean.getShopId());
                    // 下订单
                    OrderInfo info = new OrderInfo();
                    info.setUserId(bean.getUserId());
                    info.setOrderSn("N" + System.currentTimeMillis());
                    info.setShopId(bean.getShopId());
                    orderInfoService.insert(info);
                    // 修改响应状态
                    updateFlashSaleResponseStatus(1, bean);
                    Logger.info("下单成功，订单号："+info.getOrderSn());
                } else {
                    /**无库存处理**/
                    updateFlashSaleResponseStatus(2, bean);
                }
                return Boolean.TRUE;
            });
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
