package com.yeqiu.hydra.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.yeqiu.hydra.HydraUtilsManager;

import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.provider.Settings.EXTRA_APP_PACKAGE;
import static android.provider.Settings.EXTRA_CHANNEL_ID;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-07-25
 * @describe：通知工具
 * @fix：
 */
public class NotificationUtils {


    private String channelId;





    /**
     * 仅使用与API19以上 低于19的只会返回true
     *
     * @return
     */
    public static  boolean checkNotifyOpen() {

        NotificationManagerCompat manager =
                NotificationManagerCompat.from(HydraUtilsManager.getInstance().getContext());
        boolean isOpened = manager.areNotificationsEnabled();
        return isOpened;
    }


    /**
     * 跳转去通知设置
     */
    public static void toNotificationSetting() {

        Context context = HydraUtilsManager.getInstance().getContext();
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //8.0以上
                intent.putExtra(EXTRA_APP_PACKAGE, context.getPackageName());
                intent.putExtra(EXTRA_CHANNEL_ID, context.getApplicationInfo().uid);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.0以上
                intent.putExtra("app_package", context.getPackageName());
                intent.putExtra("app_uid", context.getApplicationInfo().uid);
            }

            JumpUtils.jumpToActivityByIntent(intent);
        } catch (Exception e) {

            //出现异常 直接去设置页面
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            JumpUtils.jumpToActivityByIntent(intent);
        }
    }


    /**
     * 创建通知渠道
     *
     * @param channelId
     * @param channelName
     * @param importance
     */
    public void createNotificationChannel(String channelId, String channelName,
                                          int importance) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Context context = HydraUtilsManager.getInstance().getContext();

            NotificationChannel channel = new NotificationChannel(channelId, channelName,
                    importance);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

    }



}
