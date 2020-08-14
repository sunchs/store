package com.sunchs.store.order.service.impl;

import com.sunchs.store.framework.data.Logger;
import com.sunchs.store.order.service.IFlashSaleService;
import org.springframework.stereotype.Service;

@Service
public class FlashSaleService implements IFlashSaleService {


    @Override
    public void execQueue(String s) {

        try {
            System.out.println("execQueue:"+s);

        } catch (Exception e) {
            Logger.error("处理消息队列异常，秒杀", e);
        }
    }

}
