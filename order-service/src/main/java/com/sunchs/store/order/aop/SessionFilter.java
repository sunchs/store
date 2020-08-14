package com.sunchs.store.order.aop;

import com.sunchs.store.framework.request.PostServletRequest;
import com.sunchs.store.framework.util.StreamUtil;
import com.sunchs.store.framework.util.UserThreadUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String stream = StreamUtil.getInputStream(request.getInputStream());
        UserThreadUtil.initUser(stream);
        chain.doFilter(new PostServletRequest((HttpServletRequest) request, stream), response);
        System.out.println("接口请求 ------------> : "+((HttpServletRequest) request).getRequestURL());
    }

    @Override
    public void destroy() {

    }
}
