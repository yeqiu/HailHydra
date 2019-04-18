package com.yeqiu.hydra.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/6/13
 * @describe：
 * @fix：
 */
public class KeybordUtils {

    /**
     * 打开软键盘
     *
     * @param view
     */
    public static void openKeybord(final View view) {

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
            }
        },100);
    }

    /**
     * 关闭软键盘
     *
     * @param activity
     */
    public static void closeKeybord(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm == null || activity == null || activity.isFinishing()) {
            return;
        }

        boolean softShowing = isKeyBordOpen(activity);

        LogUtils.i("softShowing == "+softShowing);

        if (softShowing) {
            if (activity.getCurrentFocus() != null) {
                //有焦点关闭
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager
                        .HIDE_NOT_ALWAYS);
            } else {
                //无焦点关闭
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }


    }


    /**
     * 判断当前软键盘是否打开
     *
     * @param activity
     * @return
     */
    public static boolean isKeyBordOpen(Activity activity) {

        if (activity == null || activity.isFinishing()) {
            return false;
        }
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //屏幕高度 减去 view可见区域 大于500 认为打开键盘
        // screenHeight - rect.bottom > 500;

        //如果view的高度+导航栏高度 大于屏幕高度

        return rect.bottom + ScreenUtils.getDaoHangHeight()<screenHeight;
    }


}
