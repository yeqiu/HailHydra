package com.yeqiu.hydra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class DateUtil {
    public static  SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyy-MM-dd",
            Locale.getDefault());
    public static  SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale
            .getDefault());
    public static  SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd" +
            " HH:mm:ss", Locale.getDefault());

    /** 格式化日期的标准字符串 */
    public final static String Detail_Format = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取北京时区
     *
     * @return
     */
    public static TimeZone getBeijingTimeZone() {
        return TimeZone.getTimeZone("GMT+8:00");
    }

    /**
     * 将日期字符串转换为Date对象
     *
     * @param date   日期字符串，必须为"yyyy-MM-dd HH:mm:ss"
     * @param format 格式化字符串
     * @return 日期字符串的Date对象表达形式
     */
    public static Date parseToDate(String date, String format) {
        Date dt = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            dt = dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return dt;
    }

    /**
     * 将date----->String
     * 将Date对象转换为指定格式的字符串
     *
     * @param date     Date对象
     * @param //String format 格式化字符串
     * @return Date对象的字符串表达形式
     */
    public static String formatDateToStr(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 当天的年月日
     * @return
     */
    public static String getTodayYyyyMmDd() {


        return YYYYMMDD_FORMAT.format(new Date());
    }

    /**
     * 当天的时分秒
     * @return
     */
    public static String getTodayHhMmSs() {
        return HHMMSS_FORMAT.format(new Date());
    }

    /**
     * 当天的年月日时分秒
     * @return
     */
    public static String getTodayYyyyMmDdHhMmSs() {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date());
    }

}
