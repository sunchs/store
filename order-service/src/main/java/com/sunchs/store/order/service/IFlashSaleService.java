package com.sunchs.store.order.service;

public interface IFlashSaleService {

    /**
     * 处理消息队列
     */
    void execQueue(String msg);
}
