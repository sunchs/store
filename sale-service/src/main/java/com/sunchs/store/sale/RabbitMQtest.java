//package com.sunchs.store.sale;
//
//import com.rabbitmq.client.*;
//import com.sunchs.store.framework.data.Logger;
//import com.sunchs.store.sale.service.impl.FlashSaleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class RabbitMQ {
//
//    public static final String exchangeName = "king.amq.fanout";
//    public static final String queueName = "king.test333";
//
//    @Autowired
//    private FlashSaleService flashSaleService;
//
//    @Bean("kingRabbit")
//    public Channel init() {
//        try {
//            ConnectionFactory factory = new ConnectionFactory();
//    // "guest"/"guest" by default, limited to localhost connections
//            factory.setUsername("admin");
//            factory.setPassword("admin");
//            factory.setVirtualHost("dms");
//            factory.setHost("localhost");
//            factory.setPort(5672);
//
//            Connection conn = factory.newConnection();
//            final Channel channel = conn.createChannel();
//
//            // 创建交换机
//            channel.exchangeDeclare(exchangeName, "direct", true);
//            // 声明队列
//            channel.queueDeclare(queueName, true, false, false, null);
//            // 将队列与交换器绑定，并设置路由码
//            channel.queueBind(queueName, exchangeName, "");
//    //        channel.basicPublish("amq.fanout", "bb", MessageProperties.TEXT_PLAIN, "123".getBytes());
//
//            channel.basicConsume(queueName, new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws
//                        IOException {
//                    String s = new String(body);
//                    System.out.println(s);
////                    Integer integer = Integer.valueOf(s);
////                    System.out.println(s);
////                    channel.basicAck(envelope.getDeliveryTag(), true);
//    //                super.handleDelivery(consumerTag, envelope, properties, body);
////                    flashSaleService.execQueue(s);
//                }
//            });
//            return channel;
//        } catch (Exception e) {
//            Logger.error("RabbitMQ异常", e);
//        }
//        return null;
//    }
//}
