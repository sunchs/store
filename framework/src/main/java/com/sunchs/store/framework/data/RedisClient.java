package com.sunchs.store.framework.data;

import com.sunchs.store.framework.util.JsonUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class RedisClient {

    private static JedisPool pool;

    private static void connect() {
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, "localhost", 6379, 5000, "");
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

    public static void delKey(String key) {
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

    public static <T> List<T> getListValue(String key, Class<T> clazz) {
        Jedis jedis = getPool().getResource();
        try {
            List<String> redisList = jedis.lrange(key, 0, -1);
            List<T> list = new ArrayList<>(redisList.size());
            for (String s : redisList) {
                list.add(JsonUtil.toObject(s, clazz));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }

    public static void setListValue(String key, String value) {
        Jedis jedis = getPool().getResource();
        try {
            jedis.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Redis异常");
        } finally {
            closeResource(jedis);
        }
    }
}
