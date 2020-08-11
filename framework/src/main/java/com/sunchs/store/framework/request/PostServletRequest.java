package com.sunchs.store.framework.request;

import com.sunchs.store.framework.util.StringUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostServletRequest extends HttpServletRequestWrapper {

    private String body = null;

    public PostServletRequest(HttpServletRequest request) {
        super(request);
    }

    public PostServletRequest(HttpServletRequest request, String body) {
        super(request);
        this.body = body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream inputStream = null;
        if(StringUtil.isNotEmpty(body)){
            inputStream =  new PostServletInputStream(body);
        }
        return inputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        String enc = getCharacterEncoding();
        if(enc == null) {
            enc = "UTF-8";
        }
        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }
}
