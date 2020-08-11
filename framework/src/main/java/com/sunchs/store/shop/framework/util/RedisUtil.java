package com.sunchs.store.shop.framework.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static JedisPool pool;

    private static void connect() {
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, "47.107.255.115", 6379, 5000, "cccccc");
    }

    private static JedisPool getPool() {
        if (pool == null) {
            connect();
        }
        return pool;
    }

    private static void closeResource(Jedis jedis) {
        try {
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean exists(String key) {
        Jedis jedis = getPool().getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }

    public static String getValue(String key) {
        Jedis jedis = getPool().getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }

    public static <T> T getValue(String key, Class<T> clazz) {
        String value = getValue(key);
        return JsonUtil.toObject(value, clazz);
    }

    public static void setValue(String key, String value) {
        Jedis jedis = getPool().getResource();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }

    public static void setValue(String key, String value, int delay) {
        Jedis jedis = getPool().getResource();
        try {
            jedis.set(key, value);
            jedis.expire(key, delay);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }

    public static <T> void setValue(String key, Class<T> clazz) {
        String value = JsonUtil.toJson(clazz);
        setValue(key, value);
    }

    public static void remove(String key) {
        Jedis jedis = getPool().getResource();
        try {
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }
}
