package com.yeqiu.hydra.app;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.yeqiu.docpreview.DocPreview;
import com.yeqiu.hydra.HydraUtilsManager;
import com.yeqiu.hydra.constant.AppKey;
import com.yeqiu.hydra.utils.APPInfoUtil;
import com.yeqiu.hydra.utils.AppUtils;

import cn.jpush.android.api.JPushInterface;

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
        DocPreview.init(app);
        initJPush(app);
        initUm(app);
        initBugly(app);

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




    private void initJPush(Application app) {

        JPushInterface.setDebugMode(true);
        JPushInterface.init(app);
    }


    private void initUm(Application app) {

        UMConfigure.setLogEnabled(true);
        UMConfigure.init(app, AppKey.UMAPPKEY, APPInfoUtil.getChannelName(),
                UMConfigure.DEVICE_TYPE_PHONE, "");
    }


    private void initBugly(Application app) {

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(app);
        strategy.setAppChannel(APPInfoUtil.getChannelName());
        strategy.setAppReportDelay(3 * 1000);
        strategy.setAppVersion(APPInfoUtil.getVersionName());
        CrashReport.initCrashReport(app, AppKey.BUGLYID,
                false, strategy);
    }
}
