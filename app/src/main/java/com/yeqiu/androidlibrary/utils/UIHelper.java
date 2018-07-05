package com.yeqiu.androidlibrary.utils;

import android.content.Context;
import android.widget.EditText;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/5
 * @describe：ui的工具类
 * @fix：
 */
public class UIHelper {

    /**
     * 防止重复点击的toast 根据时间判断 2秒内不显示相同内容
     * @param context
     * @param msg
     */
    public static void showToast(Context context,String msg){
        ToastUtils.showToast(context,msg);
    }


    /**
     * 关闭键盘
     * @param editText
     * @param context
     */
    public static void closeKeybord(EditText editText, Context context) {
        KeybordUtils.closeKeybord(editText,context);

    }

    /**
     * 将px转换为与之相等的dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将dp转换为与之相等的px
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 将px转换为sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


}
