package com.sunchs.store.test;

import com.sunchs.store.framework.data.HttpClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class FlashSaleTest {

    // 请求总数
    public static int clientTotal = 2000;
    // 同时并发执行的线程数
    public static int threadTotal = 2;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    //执行许可
                    semaphore.acquire();
                    System.out.println("请求时间：" + System.currentTimeMillis());
                    String body = "{\n" +
                            "\t\"version\": \"1.0\",\n" +
                            "\t\"platform\": \"web\",\n" +
                            "\t\"params\": {\n" +
                            "        \"shopId\": 9\n" +
                            "\t},\n" +
                            "\t\"token\": \"\"\n" +
                            "}";
                    String result = HttpClient.post("http://localhost:4000/sale-service/flashSale/buy", body);
                    System.out.println(result);
                    //释放许可
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
