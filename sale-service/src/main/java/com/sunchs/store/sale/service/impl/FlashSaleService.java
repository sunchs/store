package com.sunchs.store.sale.service.impl;

import com.sunchs.store.db.business.entity.FlashSale;
import com.sunchs.store.db.business.service.impl.FlashSaleServiceImpl;
import com.sunchs.store.framework.bean.FlashSaleQueueBean;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.Logger;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.framework.util.JsonUtil;
import com.sunchs.store.sale.bean.FlashSaleParam;
import com.sunchs.store.sale.service.IFlashSaleService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Autowired
    private FlashSaleServiceImpl flashSaleService;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void save(FlashSaleParam param) {
        try {
            FlashSale data = new FlashSale();
            data.setSaleId(param.getSaleId());
            data.setTitle(param.getTitle());
            data.setStartTime(param.getStartTime());
            data.setEndTime(param.getEndTime());
            data.setStatus(param.getStatus());
            flashSaleService.insertOrUpdate(data);
        } catch (Exception e) {
            Logger.error("保存秒杀活动信息异常", e);
        }
    }

    @Override
    public Integer buy(FlashSaleQueueBean bean) {
        // 检查活动状态
        boolean openFlashSale = isOpenFlashSale(bean.getShopId());
        if ( ! openFlashSale) {
            return 2;
        }
        // 检查库存是否充足
        int shopStock = getShopStock(bean.getShopId());
        if (shopStock <= 0) {
            return 2;
        }
        // 记录抢购等待状态（0等待抢购  1抢购成功  2抢购失败）
        String statusKey = CacheKeys.USER_FLASH_SALE_STATUS + bean.getShopId() + ":" + bean.getUserId();
        RedisClient.setValue(statusKey, 0 + "", 60 * 30);
        // 放入消息队列
        rabbitTemplate.convertAndSend("king.amq.fanout", "", JsonUtil.toJson(bean));
        // 响应"等待抢购状态"给用户
        return 0;
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
        Integer stock = RedisClient.getValue(CacheKeys.SHOP_STOCK + shopId, Integer.class);
        if (Objects.isNull(stock)) {
            return 0;
        }
        return stock.intValue();
    }
}
