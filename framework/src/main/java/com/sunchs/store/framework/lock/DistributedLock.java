package com.sunchs.store.framework.lock;

import com.sunchs.store.framework.callback.ISimpleCallback;
import com.sunchs.store.framework.data.RedisClient;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
public class DistributedLock {

    public static Object execute(String key, ISimpleCallback call, int keyExpireSeconds) {
        try {
            while (true) {
                if (RedisClient.setnx(key, key, keyExpireSeconds)) {
                    return call.execute();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            RedisClient.delKey(key);
        }
    }

    public static Object execute(String key, ISimpleCallback call) {
        return execute(key, call, 60 * 10);
    }
}
