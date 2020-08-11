package com.sunchs.store.shop.framework.bean;

import com.google.gson.JsonObject;
import com.sunchs.store.shop.framework.util.JsonUtil;

public class RequestData {

    public String version;

    public String platform;

    public Object params;

    public String token;

    private JsonObject jsonParams;

    public String getVersion() {
        return version;
    }

    public String getPlatform() {
        return platform;
    }

    public String getToken() {
        return token;
    }

    public JsonObject getParams() {
        if (jsonParams == null) {
            jsonParams = JsonUtil.toJsonObject(params);
        }
        return jsonParams;
    }

    /**
     * 将 params 转成 T 对象
     */
    public <T> T toObject(Class<T> clazz) {
        String json = JsonUtil.toJson(params);
        return JsonUtil.toObject(json, clazz);
    }

    public String getString(String key) {
        if (getParams().has(key)) {
            return getParams().get(key).getAsString();
        }
        return "";
    }

    public Integer getInt(String key) {
        if (getParams().has(key)) {
            return getParams().get(key).getAsInt();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "version='" + version + '\'' +
                ", platform='" + platform + '\'' +
                ", params=" + params +
                ", token='" + token + '\'' +
                ", jsonParams=" + jsonParams +
                '}';
    }
}
