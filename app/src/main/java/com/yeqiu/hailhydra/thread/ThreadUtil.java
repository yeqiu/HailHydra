package com.yeqiu.hailhydra.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/18
 * @describe：
 * @fix：
 */
public class ThreadUtil {


    private static Executor executor = Executors.newSingleThreadExecutor();
    private static Handler handler = new Handler(Looper.getMainLooper());


    /**
     * 切换到主线程运行
     *
     * @param runnable
     */
    public static void runOnMainThread(Runnable runnable) {


        handler.post(runnable);

    }

    /**
     * 延时切换到主线程运行
     *
     * @param runnable
     */
    public static void runOnMainThreadDelayed(Runnable runnable) {


        handler.postDelayed(runnable,1000);

    }


    /**
     * 在子线程运行
     *
     * @param runnable
     */
    public static void runOnChildThread(Runnable runnable) {

        executor.execute(runnable);

    }


}
