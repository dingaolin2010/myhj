package com.hj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateFormatUtil {

    private DateFormatUtil() {
    }

    private static final SimpleDateFormat gosdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

    // private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String date2Str(Date date) {
        return gosdf.format(date);
    }

    public static String date2Str(Date date, String format) {
        if (StringUtils.isNotEmpty(format)) {
            gosdf.applyPattern(format);
        }
        return gosdf.format(date);
    }

    public static String now2Str() {
        String str = gosdf.format(new Date());
        return str;
    }

    /*public static String now3Str() {
        String str = sdf3.format(new Date());
        return str;
    }*/

    public static String now2StrYear() {
        String str = sdf2.format(new Date());
        return str;
    }

    public static int now2Int() {
        return Integer.valueOf(now2Str());
    }

    public static Date str2Date(String str) {
        Date date = null;
        try {
            date = gosdf.parse(str);
        } catch (ParseException e) {
            new RuntimeException("String Format is not yyyyMMddHHmmss");
        }
        return date;
    }

}
