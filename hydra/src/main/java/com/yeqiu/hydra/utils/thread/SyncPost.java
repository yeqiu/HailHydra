package com.yeqiu.hydra.utils.thread;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/18
 * @describe：
 * @fix：
 */
final class SyncPost {
    boolean end = false;
    Runnable runnable;

    SyncPost(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        synchronized (this) {
            runnable.run();
            end = true;
            try {
                this.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void waitRun() {
        if (!end) {
            synchronized (this) {
                if (!end) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
