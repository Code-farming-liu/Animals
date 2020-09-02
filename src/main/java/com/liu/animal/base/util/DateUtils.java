package com.liu.animal.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/3 11:08
 **/

public class DateUtils {
    //日期转换字符串
    public static String DateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //字符串转换为日期
    public static Date StringToDate(String str, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parse = simpleDateFormat.parse(str);
        return parse;
    }
    //日期比较
    public static int compareDate(Date beginTime,Date endTime) {
        String date1 = DateToString(beginTime,"yyyy-MM-dd HH:mm:ss");
        String date2 = DateToString(endTime,"yyyy-MM-dd HH:mm:ss");
        return date1.compareTo(date2);
    }
    //判断当天是星期几
    public static String dayForWeek(Date date) throws Throwable {
        Calendar cal = Calendar.getInstance();
        String[] weekDays = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
    public static String dayForWeekNum(Date date) throws Throwable {
        Calendar cal = Calendar.getInstance();
        String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
    public static void main(String[] args) throws Throwable {
//        String str = "19991018";
//        Date date = StringToDate(str, "yyyyMMdd");
//        String str2 = "2015年02月12日";
//        Date date1 = StringToDate(str2, "yyyy年MM月dd日");
//        System.out.println(compareDate(date, date1));
//        System.out.println(date);
//        Date date = new Date();
//        System.out.println(DateUtils.dayForWeekNum(date));
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, -1);
//        System.out.println(calendar.getTime());
//        System.out.println(compareDate(date, calendar.getTime()));
//        System.out.println(dayForWeek(calendar.getTime()));
//        System.out.println(DateUtils.dayForWeek(date));
//        System.out.println(date);
        Date date = StringToDate("5/19", "MM/dd");
        System.out.println(date);

    }

}