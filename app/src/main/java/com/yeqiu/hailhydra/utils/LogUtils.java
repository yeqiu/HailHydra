package com.yeqiu.hailhydra.utils;


import com.orhanobut.logger.Logger;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16
 * @describe：log工具类
 * @fix：
 */
public class LogUtils {

    public static void init() {

//        Settings setting = Logger.init("app_log");
//        setting.logLevel(LogLevel.FULL) //  显示全部日志，LogLevel.NONE不显示日志，默认是Full
//                .methodCount(5)         //  方法栈打印的个数，默认是2
//                .methodOffset(0)        //  设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
//                .hideThreadInfo();      //  隐藏线程信息

    }


    public static void i(Object msg) {
//        init();
//        Logger.d(msg);
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





}
