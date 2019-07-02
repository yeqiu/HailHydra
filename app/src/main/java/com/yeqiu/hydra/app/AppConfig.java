package com.yeqiu.hydra.app;

import android.app.Application;

import com.yeqiu.hydra.net.HailHaydraNetIntermediary;
import com.yeqiu.hydra.utils.AppUtils;
import com.yeqiu.hydra.HydraUtilsManager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class AppConfig {

    private static AppConfig instance;

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
    }


    /**
     * 初始化工具库
     *
     * @param app
     */
    private void initHydra(Application app) {

        HydraUtilsManager.getInstance()
                .init(app)
                .setCurrentEnvironment(AppUtils.isDebug())
                .getNetConfig().setNetIntermediary(new HailHaydraNetIntermediary());


    }


}
