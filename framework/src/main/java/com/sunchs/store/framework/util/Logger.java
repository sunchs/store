package com.sunchs.store.framework.util;

public class Logger {

    public static void info(String info) {
        // 通过kafka，进行异常记录收集
        // TODO::毕业设计，不做日志收集
        System.out.println(info);
    }

    public static void error(String info, Exception e) {
        // 通过kafka，进行异常记录收集
        // TODO::毕业设计，不做日志收集
        System.out.println(info + e.getMessage());
    }
}
