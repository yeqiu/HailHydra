package com.yeqiu.hailhaydra.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
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
    private RefWatcher refWatcher;

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

        refWatcher = LeakCanary.install(this);
    }


    public RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
