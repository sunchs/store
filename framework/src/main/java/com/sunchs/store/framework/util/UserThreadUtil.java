package com.sunchs.store.framework.util;

import com.sunchs.store.framework.bean.RequestData;
import com.sunchs.store.framework.bean.UserCacheData;
import com.sunchs.store.framework.constants.CacheKeys;

import java.util.Objects;

public class UserThreadUtil {

    public static final ThreadLocal<UserCacheData> userHandle = new ThreadLocal<>();

    public static void initUser(String user) {
        RequestData data = JsonUtil.toObject(user, RequestData.class);
        if (data != null && StringUtil.isNotEmpty(data.getToken())) {
            if (RedisUtil.exists(CacheKeys.USER_LOGIN + data.getToken())) {
                UserCacheData res = RedisUtil.getValue(CacheKeys.USER_LOGIN + data.getToken(), UserCacheData.class);
                System.out.println(res);
                userHandle.set(res);
            }
        }
    }

    public static int getUserId() {
        UserCacheData user = userHandle.get();
        if (Objects.nonNull(user)) {
            return user.getId();
        }
        return 0;
    }

    public static int getType() {
        UserCacheData user = userHandle.get();
        if (Objects.nonNull(user)) {
            return user.getType();
        }
        return 0;
    }

    public static String getUserToken() {
        UserCacheData user = userHandle.get();
        if (Objects.nonNull(user)) {
            return user.getToken();
        }
        return "";
    }

    public static int getHospitalId() {
        UserCacheData user = userHandle.get();
        if (Objects.nonNull(user)) {
            return user.getHospitalId();
        }
        return 0;
    }
}
