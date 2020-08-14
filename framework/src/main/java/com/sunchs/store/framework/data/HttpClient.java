package com.sunchs.store.framework.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http请求类
 */
public class HttpClient {

    public static String post(String uri, String content) throws Exception {
        // 创建url资源
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(uri);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("POST"); // 设置请求方式
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");//设置消息头，解决508错误
            // 开始连接请求
            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // utf-8编码
            // 写入请求的字符串
            out.append(content);
            out.flush();
            out.close();
            if(conn.getResponseCode()==200){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

}