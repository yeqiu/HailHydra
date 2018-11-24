package com.yeqiu.hydrautils.utils;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.LogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16
 * @describe：log工具类
 * @fix：
 */
public class LogUtils {

    public static void init() {

        String tag = APPInfoUtil.getAppName();

        PrettyFormatStrategy strategy = PrettyFormatStrategy.newBuilder()
                .logStrategy(new LogCatStrategy())
                .tag(tag)
                .methodCount(5)
                .methodOffset(0)
                .showThreadInfo(false)
                .build();



        Logger.addLogAdapter(new AndroidLogAdapter(strategy));

    }


    public static void i(Object msg) {
        Logger.d(msg);
    }


    public static void json(String json) {
        Logger.t("json").json(json);
    }


    public static void url(String url) {
        Logger.t("json").d(url);
    }


    public static void e(String msg) {
        Logger.e(msg);
    }


    public static class LogCatStrategy implements LogStrategy {

        @Override
        public void log(int priority, String tag, String message) {
            Log.println(priority, randomKey() + tag, message);
        }

        private int last;

        private String randomKey() {
            int random = (int) (10 * Math.random());
            if (random == last) {
                random = (random + 1) % 10;
            }
            last = random;
            return String.valueOf(random);
        }
    }


}
