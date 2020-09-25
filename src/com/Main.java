package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 你的上次生日过了多少天？下次生日还有多少天？
 用Random，生成1000个[0, 10)之间的随机数，记录每个数出现次数。如果0、1、2、3、4、5、6、7、8、9每个数出现100次，说明Random生成的整数是均匀的。如果0出现200次，1出现0次，说明不均匀。
 写个方法，字符串转Date，Date parse(String s)，调用方式，如：
     Date t1 = parse("2006-01-02 03:04:05")
     Date t2 = parse("2006-01-02")
     Date t3 = parse("2006/01/02 03:04:05")
     Date t4 = parse("2006/01/02")
     注1：不可增加日期格式参数，如：Date parse(String s, String pattern)，pattern 传 yyyy-MM-dd HH:mm:ss
     注2：可用三方工具类
 写个方法，判断上/下午，boolean isAfternoon(String s)，调用方式，如：isAfternoon("2006-01-02 03:04:05")
 写个时间工具类，包括4个方法：
     获取当前时间戳（秒）
     获取当前时间，yyyy-MM-dd HH:mm:ss格式的字符串
     Date转yyyy-MM-dd HH:mm:ss格式的字符串
     任意字符串转Date
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        birthday(9, 1);

        random10();

        Date date = string2Date("2006-01-02");
        System.out.println(date.getTime());

        boolean is = isAfternoon("2006-01-02 03:04:05");
        System.out.println("2006-01-02 03:04:05是不是下午: " + (is ? "是" : "不是"));
    }


    /**
     * 你的上次生日过了多少天？下次生日还有多少天？
     * @param month 生日月份
     * @param day   生日月份的日
     * @throws ParseException
     */
    public static void birthday(int month, int day) throws ParseException {
        String monthStr = String.valueOf(month);
        if (month < 10) {
            monthStr = "0" + monthStr;
        }

        String dayStr = String.valueOf(day);
        if (day < 10) {
            dayStr = "0" + dayStr;
        }

        // 获取当前时间的年
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        String nowYearStr = String.valueOf(nowYear);

        // 今年生日所在年月日的时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayStr = nowYearStr + "-" + monthStr + "-" + dayStr;
        Date date = sdf.parse(birthdayStr);
        System.out.println(date);
        long birthdayTimestamp = date.getTime();

        // 当前时间所在的年月日
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        String nowMonthStr = String.valueOf(nowMonth);
        if (nowMonth < 10) {
            nowMonthStr = "0" + nowMonthStr;
        }
        int nowDay = calendar.get(Calendar.DATE);
        String nowDayStr = String.valueOf(nowDay);
        if (nowDay < 10) {
            nowDayStr = "0" + nowDayStr;
        }
        String nowStr = nowYearStr + "-" + nowMonthStr + "-" + nowDayStr;
        Date now = sdf.parse(nowStr);
        long nowTimestamp = now.getTime();

        // 当前时间与今年生日所差的天数
        if (birthdayTimestamp > nowTimestamp) {
            // 今年生日还没到, 上次生日就是去年的
            String lastYearStr = String.valueOf(nowYear - 1);
            String lastBirthdayStr = lastYearStr + "-" + monthStr + "-" + dayStr;
            Date lastDate = sdf.parse(lastBirthdayStr);
            long lastBirthdayTimestamp = lastDate.getTime();

            // 去年生日过去了n天
            long last = (nowTimestamp - lastBirthdayTimestamp) / 1000 / 86400;
            System.out.println("上次生日过了多少天: " + last);

            // 下次生日就是今年的
            long next = (birthdayTimestamp - nowTimestamp) / 1000 / 86400;
            System.out.println("下次生日还有多少天: " + next);

        } else {
            // 今年生日已过, 上次生日就是今年的
            long last = (nowTimestamp - birthdayTimestamp) / 1000 / 86400;
            System.out.println("上次生日过了多少天: " + last);

            // 下次生日就是明年的
            String nextYearStr = String.valueOf(nowYear + 1);
            String nextBirthdayStr = nextYearStr + "-" + monthStr + "-" + dayStr;
            Date lastDate = sdf.parse(nextBirthdayStr);
            long nextBirthdayTimestamp = lastDate.getTime();
            long next = (nextBirthdayTimestamp - nowTimestamp) / 1000 / 86400;
            System.out.println("下次生日还有多少天: " + next);
        }

    }


    /**
     * 用Random，生成1000个[0, 10)之间的随机数，记录每个数出现次数。
     * 如果0、1、2、3、4、5、6、7、8、9每个数出现100次，说明Random生成的整数是均匀的。
     * 如果0出现200次，1出现0次，说明不均匀。
     */
    public static void random10() {
        int[] arr = new int[10];

        Random random = new Random(10);

        for (int i = 0; i < 1000; ++i) {
            int num = random.nextInt(10);
            arr[num]++;
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 任意时间字符串转Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String str) throws ParseException {
        String newTime = "";
        if(str.length() > 0){
            newTime = str.replaceAll("[^\\d]", "");
        }
        System.out.println(newTime);
        String pattern = "";
        if (newTime.length() == 8) {
            pattern = "yyyyMMdd";
        } else {
            pattern = "yyyyMMddHHmmss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(newTime);
    }


    public static boolean isAfternoon(String s) throws ParseException {
        // 获取传入日期的时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s);
        System.out.println(date);
        long timestamp = date.getTime();

        // 获取中午12点的时间戳
        String time12 = s.substring(0, 10).concat(" 12:00:00");
        System.out.println(time12);
        Date time12Date = sdf.parse(time12);
        long time12Timestamp = time12Date.getTime();

        if (timestamp < time12Timestamp) {
            return false;
        }
        return true;
    }

}
