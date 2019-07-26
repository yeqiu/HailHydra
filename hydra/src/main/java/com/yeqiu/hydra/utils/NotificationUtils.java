package com.yeqiu.hydra.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.yeqiu.hydra.HydraUtilsManager;

import androidx.core.app.NotificationCompat;
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


    private final Context context;
    private NotificationManager notificationManager;

    private NotificationCompat.Builder builder;
    private String channelId;

    public NotificationUtils() {
        context = HydraUtilsManager.getInstance().getContext();
    }


    public NotificationUtils(String channelId) {
        this.channelId = channelId;
        context = HydraUtilsManager.getInstance().getContext();
        getBuilder();
    }


    private NotificationCompat.Builder getBuilder() {

        if (builder == null) {
            builder = new NotificationCompat.Builder(context, channelId);
        }

        return builder;
    }


    /**
     * 仅使用与API19以上 低于19的只会返回true
     *
     * @return
     */
    public boolean checkNotifyOpen() {

        NotificationManagerCompat manager =
                NotificationManagerCompat.from(HydraUtilsManager.getInstance().getContext());
        boolean isOpened = manager.areNotificationsEnabled();
        return isOpened;
    }


    /**
     * 跳转去通知设置
     */
    public void toNotificationSetting() {

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


    /**
     * 创建NotificationManager的对象
     *
     * @return
     */
    public NotificationManager getNotificationManager() {

        if (notificationManager == null) {
            notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }


    /**
     * 设置点击意图
     *
     * @param intent
     * @return
     */
    public NotificationUtils setIntent(PendingIntent intent) {
        getBuilder().setContentIntent(intent);
        return this;
    }


    /**
     * 设置小图标
     *
     * @param smallIcon
     * @return
     */
    public NotificationUtils setSmallIcon(int smallIcon) {
        getBuilder().setSmallIcon(smallIcon);
        return this;
    }

    /**
     * 设置大图标
     *
     * @param largeIcon
     * @return
     */
    public NotificationUtils setLargeIcon(Bitmap largeIcon) {
        getBuilder().setLargeIcon(largeIcon);
        return this;
    }

    /**
     * 设置时间
     *
     * @param when
     * @return
     */
    public NotificationUtils setWhen(long when) {
        getBuilder().setWhen(when);
        return this;
    }

    /**
     * 设置自动取消
     *
     * @param autoCancel
     * @return
     */
    public NotificationUtils setAutoCancel(boolean autoCancel) {
        getBuilder().setAutoCancel(autoCancel);
        return this;
    }

    /**
     * 设置成大号通知栏
     * 在有些手机上通知栏是伸缩的（vivo）
     *
     * @param contentTitle
     * @param contentText
     * @return
     */
    public NotificationUtils setMoreline(String contentTitle, String contentText) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(contentText)
                    .setBigContentTitle(contentTitle));
        }
        return this;
    }


    /**
     * 设置标题
     *
     * @param contentTitle
     * @return
     */
    public NotificationUtils setContentTitle(String contentTitle) {
        getBuilder().setContentTitle(contentTitle);
        return this;
    }

    /**
     * 设置内容
     *
     * @param contentText
     * @return
     */
    public NotificationUtils setContentText(String contentText) {
        getBuilder().setContentText(contentText);
        return this;
    }

    public NotificationUtils create(int notifyId, String contentTitle, String contentText) {

        getBuilder().setContentText(contentTitle).setContentText(contentText);

        Notification notification = getBuilder().build();
        getNotificationManager().notify(notifyId, notification);

        return this;

    }


    /**
     * 清空所有的通知
     */
    public NotificationUtils cancelAll() {
        getNotificationManager().cancelAll();

        return this;
    }

    /**
     * 清空所有的通知
     */
    public NotificationUtils cance(int notifyId) {
        getNotificationManager().cancel(notifyId);
        return this;
    }

}
