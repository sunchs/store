package com.sunchs.store.sale.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sunchs.store.db.business.entity.FlashSale;
import com.sunchs.store.db.business.entity.FlashSaleShop;
import com.sunchs.store.db.business.service.impl.FlashSaleServiceImpl;
import com.sunchs.store.db.business.service.impl.FlashSaleShopServiceImpl;
import com.sunchs.store.framework.bean.FlashSaleQueueBean;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.Logger;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.framework.enums.DataStatusEnum;
import com.sunchs.store.framework.util.FormatUtil;
import com.sunchs.store.framework.util.JsonUtil;
import com.sunchs.store.sale.bean.FlashSaleParam;
import com.sunchs.store.sale.bean.FlashSaleShopParam;
import com.sunchs.store.sale.service.IFlashSaleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Autowired
    private FlashSaleServiceImpl flashSaleService;
    @Autowired
    private FlashSaleShopServiceImpl flashSaleShopService;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void save(FlashSaleParam param) {
        try {
            FlashSale data = new FlashSale();
            data.setSaleId(param.getSaleId());
            data.setTitle(param.getTitle());
            data.setStartTime(FormatUtil.dateTime(param.getStartTime()));
            data.setEndTime(FormatUtil.dateTime(param.getEndTime()));
            data.setStatus(param.getStatus());
            if (flashSaleService.insertOrUpdate(data)) {
                // 保存抢购产品
                saveFlashSaleShop(param.getShopList(), data);
            }
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
        List<FlashSale> flashSaleList = RedisClient.getListValue(CacheKeys.SHOP_FLASH_SALE_TIME_LIST + shopId, FlashSale.class);
        if (CollectionUtils.isEmpty(flashSaleList)) {
            return false;
        }
        Date time = new Date(System.currentTimeMillis());
        for (FlashSale flashSale : flashSaleList) {
            if (Objects.equals(flashSale.getStatus(), 1) && time.after(flashSale.getStartTime()) && time.before(flashSale.getEndTime())) {
                return true;
            }
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

    /**
     * 保存抢购产品
     */
    private void saveFlashSaleShop(List<FlashSaleShopParam> shopList, FlashSale flashSale) {
        // 清理历史数据
        Wrapper<FlashSaleShop> wrapper = new EntityWrapper<FlashSaleShop>()
                .eq(FlashSaleShop.SALE_ID, flashSale.getSaleId());
        flashSaleShopService.delete(wrapper);
        // 保存新数据
        for (FlashSaleShopParam shopParam : shopList) {
            FlashSaleShop shop = new FlashSaleShop();
            shop.setSaleId(flashSale.getSaleId());
            shop.setShopId(shopParam.getShopId());
            shop.setPrice(new BigDecimal(shopParam.getPrice()));
            flashSaleShopService.insert(shop);
        }
        // 更新缓存
        for (FlashSaleShopParam shopParam : shopList) {
            int shopId = shopParam.getShopId();
            // 获取该商品对应的秒杀活动
            Wrapper<FlashSaleShop> saleShopWrapper = new EntityWrapper<FlashSaleShop>()
                    .setSqlSelect(FlashSaleShop.SALE_ID.concat(" as saleId"))
                    .eq(FlashSaleShop.SHOP_ID, shopId);
            List<Integer> saleIds = flashSaleShopService.selectList(saleShopWrapper).stream().map(FlashSaleShop::getSaleId).collect(Collectors.toList());
            Wrapper<FlashSale> flashSaleWrapper = new EntityWrapper<FlashSale>()
                    .in(FlashSale.SALE_ID, saleIds)
                    .eq(FlashSale.STATUS, DataStatusEnum.ENABLE.value);
            List<FlashSale> flashSaleList = flashSaleService.selectList(flashSaleWrapper);
            // 重新刷新缓存
            RedisClient.delKey(CacheKeys.SHOP_FLASH_SALE_TIME_LIST + shopId);
            for (FlashSale sale : flashSaleList) {
                RedisClient.setListValue(CacheKeys.SHOP_FLASH_SALE_TIME_LIST + shopId, JsonUtil.toJson(sale));
            }
        }
    }
}
