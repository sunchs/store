package com.sunchs.store.framework.util;

public class Logger {

    public static void info(String info) {
        System.out.println(info);
    }

    public static void error(String info, Exception e) {
        System.out.println(info + e.getMessage());
    }
}
