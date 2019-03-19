package com.yeqiu.hydra.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.yeqiu.hydra.HydraUtilsManager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class ResourceUtil {

    /**
     * 获得字符串
     *
     * @param strId 字符串id
     * @return 字符串
     */
    public static String getString(int strId) {
        Context context = HydraUtilsManager.getInstance().getContext();
        return context.getResources().getString(strId);
    }

    /**
     * 获得颜色
     *
     * @param colorId 颜色id
     * @return 颜色
     */
    public static int getColor(int colorId) {
        Context context = HydraUtilsManager.getInstance().getContext();

        return ContextCompat.getColor(context, colorId);
    }

    /**
     * 获得Drawable
     *
     * @param drawableId Drawable的id
     * @return Drawable
     */
    public static Drawable getDrawable(int drawableId) {
        Context context = HydraUtilsManager.getInstance().getContext();
        return ContextCompat.getDrawable(context, drawableId);
    }
}
