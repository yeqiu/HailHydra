package com.yeqiu.hydrautils;

import android.content.Context;

import com.yeqiu.hydrautils.common.LogUtils;
import com.yeqiu.hydrautils.ui.UiConfig;

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
    public void init(Context context) {

        this.context = context;
        if (context == null) {
            throw new NullPointerException("context must not null,Please check init()");
        }


        LogUtils.init();

    }


    public UiConfig getUiConfig() {
        return UiConfig.getInstance();
    }


    public Context getContext() {


        if (context == null) {
            throw new NullPointerException("context must not null,Please check init()");
        }


        return context;
    }
}
