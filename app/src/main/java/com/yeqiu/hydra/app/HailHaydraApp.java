package com.yeqiu.hydra.app;

import android.support.multidex.MultiDexApplication;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class HailHaydraApp extends MultiDexApplication {


    private static HailHaydraApp app;

    public HailHaydraApp() {
        app = this;
    }

    public static synchronized HailHaydraApp getInstance() {
        if (app == null) {
            app = new HailHaydraApp();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //初始化第三方的配置
        AppConfig.getInstance().init(this);
    }
}
