package com.sunchs.store.shop.framework.util;

import java.text.DecimalFormat;

public class NumberUtil {

    public static boolean isZero(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof Integer || value instanceof Long) {
            if (value.equals(0)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isZero(int value) {
        if (value == 0) {
            return true;
        }
        return false;
    }

    public static boolean nonZero(int value) {
        return ! isZero(value);
    }

    public static double format(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        String num = df.format(value);
        return Double.valueOf(num).doubleValue();
    }


}
