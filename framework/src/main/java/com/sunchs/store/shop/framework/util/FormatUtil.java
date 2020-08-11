package com.sunchs.store.shop.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateTime(Date date) {
        return dateFormat.format(date);
    }

    public static Date dateTime(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("时间解析异常" + e.getMessage());
        }
        return null;
    }
}
