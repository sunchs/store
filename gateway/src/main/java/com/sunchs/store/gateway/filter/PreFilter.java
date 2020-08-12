package com.sunchs.store.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sunchs.store.framework.bean.RequestData;
import com.sunchs.store.framework.bean.ResultData;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.util.JsonUtil;
import com.sunchs.store.framework.util.RedisClient;
import com.sunchs.store.framework.util.StreamUtil;
import com.sunchs.store.framework.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());

        /**
         * 忽略部分
         */
        Set<String> ignoreUri = new HashSet<>();
        ignoreUri.add("/user-service/user/login");
        if (ignoreUri.contains(request.getRequestURI())) {
            return null;
        }

        String stream = null;
        try {
            stream = StreamUtil.getInputStream(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 检查参数
         */
        if (StringUtil.isEmpty(stream)) {
            rejectRequest(ResultData.getFailure("基本参数:[ 请输入参数 ]"));
            return null;
        }

        RequestData data = JsonUtil.toObject(stream, RequestData.class);
        /**
         * 检查参数合法性
         */
        if (Objects.isNull(data)) {
            rejectRequest(ResultData.getFailure("基本参数:[ JSON语法不正确 ]"));
            return null;
        }

        /**
         * 检查 版本
         */
        if (StringUtil.isEmpty(data.getVersion())) {
            rejectRequest(ResultData.getFailure("基本参数:[ 版本 不能为空 ]"));
            return null;
        }

        /**
         * 检查 平台
         */
        if (StringUtil.isEmpty(data.getPlatform())) {
            rejectRequest(ResultData.getFailure("基本参数:[ 平台 不能为空 ]"));
            return null;
        }

        /**
         * 检查 Token
         */
        if (StringUtil.isEmpty(data.getToken())) {
            rejectRequest(ResultData.getFailure("基本参数:[ Token 不能为空 ]"));
            return null;
        } else {
            boolean exists = RedisClient.exists(CacheKeys.USER_LOGIN + data.getToken());
            if ( ! exists) {
                rejectRequest(ResultData.getLoginFailure());
                return null;
            }
        }

        return null;
    }

    private void rejectRequest(ResultData resultData) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(JsonUtil.toJson(resultData));
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
    }
}
