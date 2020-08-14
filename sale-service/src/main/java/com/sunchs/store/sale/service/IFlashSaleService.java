package com.sunchs.store.sale.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.sunchs.store.sale.bean.FlashSaleParam;

import java.io.IOException;

public interface IFlashSaleService {

    /**
     * 抢购
     */
    Integer buy(FlashSaleParam param);

//    /**
//     * 处理消息队列
//     */
//    void execQueue(String s);
}
