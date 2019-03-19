package com.yeqiu.hydra.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.yeqiu.hydra.HydraUtilsManager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class ScreenUtils {


    /**
     * 横竖屏
     *
     * @return true 竖屏 false 横屏
     */
    public static boolean isScreenPortrait() {

        Context context = HydraUtilsManager.getInstance().getContext();
        return context.getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_PORTRAIT;
    }


    /**
     * 获得状态栏的高度
     *
     * @return
     */
    public static int getStatusHeight() {

        Context context = HydraUtilsManager.getInstance().getContext();
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {

        Context context = HydraUtilsManager.getInstance().getContext();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {

        Context context = HydraUtilsManager.getInstance().getContext();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {

        Context context = HydraUtilsManager.getInstance().getContext();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {

        Context context = HydraUtilsManager.getInstance().getContext();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param px
     * @return
     */
    public static float px2dp(float px) {
        Context context = HydraUtilsManager.getInstance().getContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (px / scale);
    }

    /**
     * px转sp
     *
     * @param px
     * @return
     */
    public static float px2sp(float px) {

        Context context = HydraUtilsManager.getInstance().getContext();
        return (px / context.getResources().getDisplayMetrics().scaledDensity);
    }


    /**
     * 获取当前屏幕截图，包含状态栏 但状态栏是空白
     */
    public static Bitmap screenShotWithStatusBar(Activity activity) {

        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     */
    public static Bitmap screenShotWithoutStatusBar(Activity activity) {

        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }


    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = HydraUtilsManager.getInstance().getContext().getResources().getIdentifier
                ("status_bar_height",
                        "dimen",
                        "android");
        if (resourceId > 0) {
            result = HydraUtilsManager.getInstance().getContext().getResources()
                    .getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     *
     * @return
     */
    public static int getDaoHangHeight() {
        int result = 0;
        int resourceId = 0;
        int rid = HydraUtilsManager.getInstance().getContext().getResources().getIdentifier("config_showNavigationBar", "bool",
                "android");
        if (rid != 0) {
            resourceId = HydraUtilsManager.getInstance().getContext().getResources().getIdentifier("navigation_bar_height", "dimen",
                    "android");
            return HydraUtilsManager.getInstance().getContext().getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }

    }
}
