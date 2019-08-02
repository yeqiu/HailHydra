package com.yeqiu.hydra.app;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-07-31
 * @describe：
 * @fix：
 */

import com.yeqiu.hydra.utils.APPInfoUtil;

/**
 * 捕捉bug，并写入日志文件 自定义的 异常处理类 , 实现了 UncaughtExceptionHandler接口
 *
 * @author lsw
 * @date 2014-3-31
 */

public class BugHandler implements Thread.UncaughtExceptionHandler {


    private static BugHandler myCrashHandler;

    private BugHandler() {

    }

    public static synchronized BugHandler getInstance() {
        if (myCrashHandler != null) {
            return myCrashHandler;
        } else {
            myCrashHandler = new BugHandler();
            return myCrashHandler;
        }
    }



    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        APPInfoUtil.restart();


    }



}
