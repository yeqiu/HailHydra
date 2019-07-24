package com.yeqiu.hydra.utils;

import android.content.Context;

import com.yeqiu.hydra.HydraUtilsManager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class DensityUtils {

    /**
     * 将px转换为与之相等的dp
     */
    public static int px2dp(float pxValue) {

        Context context = HydraUtilsManager.getInstance().getContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将dp转换为与之相等的px
     */
    public static int dp2px(float dipValue) {

        Context context = HydraUtilsManager.getInstance().getContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 将px转换为sp
     */
    public static int px2sp(float pxValue) {

        Context context = HydraUtilsManager.getInstance().getContext();
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 将sp转换为px
     */
    public static int sp2px(float spValue) {

        Context context = HydraUtilsManager.getInstance().getContext();
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }





}
