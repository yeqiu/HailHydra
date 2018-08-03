package com.yeqiu.hailhydra.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/6/1
 * @describe：网络监听
 * @fix：
 */
public class NetWorkStateReceiver extends BroadcastReceiver {
    private OnNetWorkStateChangeListener onNetWorkStateChangeListener;
    private OnNetWorkConnectChangeListener onNetWorkConnectChangeListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService
                (CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            switch (networkInfo.getType()) {
                case TYPE_MOBILE:
                    //netStatus.setText("正在使用2G/3G/4G网络");
                    //正在使用2G/3G/4G网络
                    if (onNetWorkStateChangeListener != null) {
                        onNetWorkStateChangeListener.onMobile();
                    }
                    if (onNetWorkConnectChangeListener != null) {
                        onNetWorkConnectChangeListener.onConnect();
                    }
                    break;
                case TYPE_WIFI:
                    //wifi
                    if (onNetWorkStateChangeListener != null) {
                        onNetWorkStateChangeListener.onWifi();
                    }
                    if (onNetWorkConnectChangeListener != null) {
                        onNetWorkConnectChangeListener.onConnect();
                    }
                    break;
                default:
                    break;
            }
        } else {
            //当前无网络连接
            if (onNetWorkStateChangeListener != null) {
                onNetWorkStateChangeListener.onNoNet();
            }
            if (onNetWorkConnectChangeListener != null) {
                onNetWorkConnectChangeListener.onNoNet();
            }
        }

    }


    public void setOnNetWorkStateChangeListener(OnNetWorkStateChangeListener
                                                        onNetWorkStateChangeListener) {

        this.onNetWorkStateChangeListener = onNetWorkStateChangeListener;
    }


    public void setOnNetWorkConnectChangeListener(OnNetWorkConnectChangeListener
                                                       onNetWorkConnectChangeListener) {

        this.onNetWorkConnectChangeListener = onNetWorkConnectChangeListener;
    }

    public interface OnNetWorkStateChangeListener {
        void onMobile();

        void onWifi();

        void onNoNet();
    }


    public interface OnNetWorkConnectChangeListener {
        void onConnect();

        void onNoNet();

    }


}
