package com.sunchs.store.order.config;

import com.sunchs.store.order.service.impl.FlashSaleService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "king.test333")
public class MessageReceive {

    @Autowired
    private FlashSaleService flashSaleService;

    @RabbitHandler
    public void process(String message) {
        flashSaleService.execQueue(message);
    }
}