package com.yeqiu.hailhaydra.app;

import android.app.Application;

import com.yeqiu.hydrautils.HydraUtilsManager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/13
 * @describe：
 * @fix：
 */
public class HailHaydraApp extends Application {

    private static HailHaydraApp instance;

    public HailHaydraApp() {
        instance = this;
    }

    public static synchronized HailHaydraApp getInstance() {
        if (instance == null) {
            instance = new HailHaydraApp();
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        HydraUtilsManager.getInstance().init(this);
    }
}
