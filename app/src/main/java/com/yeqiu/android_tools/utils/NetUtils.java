package com.yeqiu.android_tools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author ye
 * @date 2018/4/2
 * @desc网络状态工具类
 */

public class NetUtils {

    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            return connectivity.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        }
    }

    /**
     * 判断是否是移动数据连接
     */
    public static boolean isMobileData(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            return connectivity.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE;
        }
    }
}
