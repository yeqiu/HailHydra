package com.yeqiu.androiddome.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * 时间管理工具类
 * 此类包含多种对时间管理方面操作
 * 如：计时，倒计时，线程倒计时，获取时间（12小时制与24小时制）等...
 */

public class TimeUtils {

    private TextView timeTv;          //计时组件
    private long start;               //开始时间
    private long end;                 //结束时间
    private boolean flag = false;     //是否在计时
    private Handler handler;

    /**
     * 构造器
     */
    private TimeUtils() {
    }

    private static TimeUtils instance;

    public static synchronized TimeUtils getInstance() {
        if (instance == null) {
            instance = new TimeUtils();
        }
        return instance;
    }

    //--------------------------倒计时模块---------------------------------

    /**
     * 倒计时
     *
     * @param allMillis 设置总时间
     * @param millis    时间间隔
     * @param textView  容器组件
     *                  倒计时改变颜色
     */
    public void countDown(final Context context, int allMillis, int millis, final TextView
            textView) {
        CountDownTimer countDownTimer = new CountDownTimer(allMillis * 1000, millis) {
            @Override
            public void onTick(long allMillis) {
                textView.setClickable(false);
                textView.setText(allMillis / 1000 + "秒重新获取");
                textView.setEnabled(false);
                textView.setTextColor(Color.parseColor("#808080"));
            }

            /**
             * 计时完成
             */
            @Override
            public void onFinish() {
                textView.setClickable(true);
                textView.setText("获取验证码");
                textView.setEnabled(true);
                textView.setTextColor(Color.parseColor("#5eddbf"));
            }
        };
        countDownTimer.start();
    }

    //---------------------------获取时间静态方法-----------------------

    /**
     * 获取当前时间毫秒数
     *
     * @return 当前毫秒时间
     */
    public static long getTimeMill() {
        return System.currentTimeMillis();
    }

    /**
     * 根据格式返回当前系统时间
     * 例: 格式为yyyy-MM-dd HH:mm:ss
     *
     * @param format 定义的可匹配的显示格式
     * @return string 格式化的时间
     */
    public static String getCurrencyTimeString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间 格式为： 年.月.日
     */
    public static String getYMD() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);//获得固定的日期
        return format;
    }

    /**
     * 根据格式返回当前系统时间  格式为yyyy-MM-dd hh:mm:ss
     * hh:mm:ss 12小时制
     */
    public static String getCurrencyTimeString() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 根据格式返回当前系统时间  格式为yyyy-MM-dd HH:mm:ss
     * HH:mm:ss 24小时制
     */
    public static String getAllDayCurrencyTimeString() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取时间  时间格式为 HH:mm:ss
     */
    public static String getTime(int ms) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(ms);
        return hms;
    }


    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     */
    public static long dataToLong(String time) {
        long l = 0L;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            l = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     */
    public static String timeTodate(Long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = sdr.format(new Date(time * 1000L));
        return times;

    }





    public static class TimeDate {
        private long day;
        private long hour;
        private long minutes;
        private long seconds;

        public long getDay() {
            return day;
        }

        public void setDay(long day) {
            this.day = day;
        }

        public long getHour() {
            return hour;
        }

        public void setHour(long hour) {
            this.hour = hour;
        }

        public long getMinutes() {
            return minutes;
        }

        public void setMinutes(long minutes) {
            this.minutes = minutes;
        }

        public long getSeconds() {
            return seconds;
        }

        public void setSeconds(long seconds) {
            this.seconds = seconds;
        }
    }

    public static TimeDate formatTime(Long ms) {

        TimeDate ret = new TimeDate();
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }

        ret.setDay(day);
        ret.setHour(hour);
        ret.setMinutes(minute);
        ret.setSeconds(second);
        return ret;
    }


}
