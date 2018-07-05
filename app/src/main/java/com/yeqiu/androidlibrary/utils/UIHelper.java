package com.yeqiu.androidlibrary.utils;

import android.content.Context;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/5
 * @describe：ui的工具类
 * @fix：
 */
public class UIHelper {

    public static void showToast(Context context,String msg){
        ToastUtils.showToast(context,msg);
    }
}
