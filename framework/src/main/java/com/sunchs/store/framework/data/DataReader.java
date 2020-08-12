package com.sunchs.store.framework.data;

import com.sunchs.store.framework.callback.IDataGeter;
import com.sunchs.store.framework.callback.IListDataGeter;
import com.sunchs.store.framework.util.JsonUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class DataReader {

    /**
     * 获取对象数据
     * 1、从Redis获取数据
     * 2、Redis没有数据，从Mysql获取数据
     * 3、Mysql数据存储到Redis里
     */
    public static <T> T getData(String cacheKey, Class<T> clazz, IDataGeter<T> dataGeter) {
        try {
            T value = RedisClient.getValue(cacheKey, clazz);
            if (Objects.isNull(value)) {
                value = dataGeter.getData();
                if (Objects.nonNull(value)) {
                    RedisClient.setValue(cacheKey, JsonUtil.toJson(value));
                }
            }
            return value;
        } catch (Exception e) {
            Logger.error("读取Redis数据异常：key:" + cacheKey, e);
        }
        return null;
    }

    /**
     * 获取列表数据
     * 1、从Redis获取数据
     * 2、Redis没有数据，从Mysql获取数据
     * 3、Mysql数据存储到Redis里
     */
    public static <T> List<T> getListData(String cacheKey, Class<T> clazz, IListDataGeter<T> dataGeter) {
        try {
            List<T> listValue = RedisClient.getListValue(cacheKey, clazz);
            if (CollectionUtils.isEmpty(listValue)) {
                listValue = dataGeter.getList();
                if (CollectionUtils.isNotEmpty(listValue)) {
                    for (T t : listValue) {
                        RedisClient.setListValue(cacheKey, JsonUtil.toJson(t));
                    }
                }
            }
            return listValue;
        } catch (Exception e) {
            Logger.error("读取Redis数据异常：key:" + cacheKey, e);
        }
        return null;
    }
}
