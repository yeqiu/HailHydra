package com.yeqiu.hydrautils.thread;

import android.os.Looper;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/15
 * @describe：
 * @fix：
 * 参考资料
 * https://blog.csdn.net/qiujuer/article/details/41900879
 */
public class ThreadUtils {


    private static ThreadHandler threadHandler = null;

    private static ThreadHandler getThreadHandler() {
        if (threadHandler == null) {
            synchronized (ThreadUtils.class) {
                if (threadHandler == null) {
                    threadHandler = new ThreadHandler(Looper.getMainLooper(), 2000);
                }
            }
        }
        return threadHandler;
    }

    /**
     * 进入主线程执行 runnable，执行完成后继续切换到子线程
     *
     * @param runnable Runnable Interface
     */
    public static void runOnMainThreadAndStayChild(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return;
        }
        getThreadHandler().async(runnable);
    }

    /**
     * 进入主线程执行 runnable，执行完成后继续留在主线程
     *
     * @param runnable Runnable Interface
     */
    public static void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return;
        }
        SyncPost poster = new SyncPost(runnable);
        getThreadHandler().sync(poster);
        poster.waitRun();
    }

    public static void dispose() {
        if (threadHandler != null) {
            threadHandler.dispose();
            threadHandler = null;
        }
    }


}
