package com.yeqiu.hydra.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.yeqiu.docpreview.DocPreview;
import com.yeqiu.hydra.HydraUtilsManager;
import com.yeqiu.hydra.utils.AppUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class AppConfig {

    private static AppConfig instance;
    private RefWatcher refWatcher;

    private AppConfig() {
    }

    public static AppConfig getInstance() {

        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }

        return instance;
    }


    /**
     * 初始化各种配置
     *
     * @param app
     */
    public void init(Application app) {
        initHydra(app);

        initLeakCanary(app);

        DocPreview.init(app);

    }


    /**
     * 初始化工具库
     *
     * @param app
     */
    private void initHydra(Application app) {

        HydraUtilsManager.getInstance()
                .init(app)
                .setCurrentEnvironment(AppUtils.isDebug());


    }


    private void initLeakCanary(Application app) {
        refWatcher = LeakCanary.install(app);
    }


    public RefWatcher getRefWatcher() {
        return refWatcher;
    }


}
