package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author song
 * 获取当前时间戳（秒）
 * 获取当前时间，yyyy-MM-dd HH:mm:ss格式的字符串
 * Date转yyyy-MM-dd HH:mm:ss格式的字符串
 * 任意字符串转Date
 */
public class DateTool {
    public static void main(String[] args) throws ParseException {
        System.out.println(getNowTimestamp());
        System.out.println(getNowDate());

        System.out.println(getDate(new Date()));

        System.out.println(getDate(str2Date("2020-09-24 22:59:59")));
        System.out.println(getDate(str2Date("2020-09-24 22:59")));
        System.out.println(getDate(str2Date("2020-09-24 22")));
        System.out.println(getDate(str2Date("2020-09-24")));
        System.out.println(getDate(str2Date("2020-09")));
        System.out.println(getDate(str2Date("2020")));
        System.out.println(getDate(str2Date("20")));
    }

    public static long getNowTimestamp() {
        return System.currentTimeMillis();
    }


    public static String getNowDate() {
        return getDate(new Date());
    }


    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date str2Date(String str) throws ParseException {
        String newTime = "";
        if(str.length() > 0){
            newTime = str.replaceAll("[^\\d]", "");
        }
        String pattern = "";
        switch (newTime.length()) {
            case 4: pattern = "yyyy";
            break;
            case 6: pattern = "yyyyMM";
            break;
            case 8: pattern = "yyyyMMdd";
            break;
            case 10: pattern = "yyyyMMddHH";
            break;
            case 12: pattern = "yyyyMMddHHmm";
            break;
            case 14: pattern = "yyyyMMddHHmmss";
            break;
            default: throw new ParseException("日期格式不正确", 0);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(newTime);
    }
}
