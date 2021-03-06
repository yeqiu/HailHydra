package com.yeqiu.hydra;

import android.app.Application;
import android.content.Context;

import com.yeqiu.hydra.net.NetConfig;
import com.yeqiu.hydra.ui.UiConfig;
import com.yeqiu.hydra.utils.LogUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/6
 * @describe：
 * @fix：
 */
public class HydraUtilsManager {

    private static HydraUtilsManager instance;
    private Context context;
    private boolean isDebug = true;


    private HydraUtilsManager() {
        instance = this;
    }

    public static synchronized HydraUtilsManager getInstance() {
        if (instance == null) {
            instance = new HydraUtilsManager();
        }
        return instance;
    }


    /**
     * 初始化
     *
     * @param context
     */
    public HydraUtilsManager init(Application context) {

        this.context = context;
        if (context == null) {
            throw new NullPointerException("context must not null,Please check init()");
        }

        //初始化日志库
        LogUtils.init();
        //初始化网络请求
        NetConfig.getInstance().init(context);


        return getInstance();

    }


    /**
     * 设置当前环境
     *
     * @param isDebug
     * @return
     */
    public HydraUtilsManager setCurrentEnvironment(boolean isDebug) {

        this.isDebug = isDebug;
        return getInstance();
    }


    public UiConfig getUiConfig() {
        return UiConfig.getInstance();
    }

    public NetConfig getNetConfig(){
        return NetConfig.getInstance();
    }


    public Context getContext() {

        if (context == null) {
            throw new NullPointerException("context must not null,Please check init()");
        }

        return context;
    }


    public boolean isDebug() {
        return isDebug;
    }


}