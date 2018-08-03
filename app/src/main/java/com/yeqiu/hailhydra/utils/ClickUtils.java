package com.yeqiu.hailhydra.utils;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14 上午11:34
 * @describe：按钮双击判断
 * @fix：
 */
public class ClickUtils {


    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;


    public static boolean isValid() {
        boolean isValid = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            //当前事件 - 上次事件 超过约定事件，视为点击有效 记录当前时间为上次时间
            isValid = true;
            lastClickTime = curClickTime;
        }
        return isValid;
    }

}
