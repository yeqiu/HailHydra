package com.yeqiu.androidlibrary.utils;

import android.content.Context;
import android.content.IntentFilter;

import com.yeqiu.androidlibrary.receiver.NetWorkStateReceiver;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/6/1
 * @describe：
 * @fix：
 */
public class NetWorkUtils {

    private static NetWorkStateReceiver networkChangeReceiver;
    private static Context context;


    /**
     * 注册网络监听 监听网络连接的状态 流量 wifi
     *
     * @param context
     * @param onNetWorkStateChangeListener
     */
    public static void turnOnListnerWithState(Context context, NetWorkStateReceiver
            .OnNetWorkStateChangeListener onNetWorkStateChangeListener) {


        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            networkChangeReceiver = new NetWorkStateReceiver();
            networkChangeReceiver.setOnNetWorkStateChangeListener(onNetWorkStateChangeListener);
            context.registerReceiver(networkChangeReceiver, intentFilter);
        }
    }


    /**
     * 注册网络监听 仅仅监听有网和没网
     *
     * @param context
     */
    public static void turnOnListnerWithoutState(Context context, NetWorkStateReceiver
            .OnNetWorkConnectChangeListener onNetWorkConnectChangeListener) {
        NetWorkUtils.context = context;
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            networkChangeReceiver = new NetWorkStateReceiver();
            networkChangeReceiver.setOnNetWorkConnectChangeListener(onNetWorkConnectChangeListener);
            context.registerReceiver(networkChangeReceiver, intentFilter);
        }
    }


    /**
     * 注销网络监听
     */
    public static void turnOffListner() {
        if (context != null) {
            context.unregisterReceiver(networkChangeReceiver);
        }


    }
}
