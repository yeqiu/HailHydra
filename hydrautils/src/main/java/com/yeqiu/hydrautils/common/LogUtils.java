package com.yeqiu.hydrautils.common;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16
 * @describe：log工具类
 * @fix：
 */
public class LogUtils {

    public static void init() {

        Settings setting = Logger.init(APPInfoUtil.getAppName());
        //  显示全部日志，LogLevel.NONE不显示日志，默认是Full
        setting.logLevel(LogLevel.FULL)
                //  方法栈打印的个数，默认是2
                .methodCount(5)
                //  设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
                .methodOffset(0)
                //  隐藏线程信息
                .hideThreadInfo();

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

}
