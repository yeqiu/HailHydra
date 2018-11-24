package com.yeqiu.hydrautils.utils;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydrautils.HydraUtilsManager;
import com.yeqiu.hydrautils.R;

import java.io.File;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/23
 * @describe：升级工具
 * @fix：
 */
public class UpdateUtil {

    private String appName;
    private String downUrl;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private Context context = HydraUtilsManager.getInstance().getContext();
    private static final String CHANNEL_ID = "panda_update_id";
    private static final CharSequence CHANNEL_NAME = "panda_update_channel";
    private static final int NOTIFY_ID = 0;
    private OnDownloadProgress onDownloadProgress;

    /**
     * 自动下载 apk
     * 判断本地是否已存在
     * 下载完成后自动跳转去安装
     * 处理顶部通知栏进度
     *
     * @param downUrl
     * @param versionName
     */
    public void update(String downUrl, String versionName) {

        this.downUrl = downUrl;
        appName = APPInfoUtil.getPackageNameLast() + "_" + versionName + ".apk";
        //appName 示例 QQ_1.1.apk


        boolean isExists = fileIsExists(HydraUtilsManager.getInstance().getContext()
                .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/" + appName);

        // TODO: 2018/11/23 判断是否存在  存在的文件是否正确
        downAPK(downUrl);

    }

    private void downAPK(String downloadUrl) {

        if (!checkSDCardPermission()) {
            return;
        }

        String path = HydraUtilsManager.getInstance().getContext()
                .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath() + "/";

        setUpNotification();

        OkGo.<File>get(downloadUrl)
                .tag(this)
                .execute(new FileCallback(path, appName) {

                    //避免通知栏刷新过快
                    int oldRate = 0;

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {

                    }

                    @Override
                    public void onSuccess(Response<File> response) {

                        installApp(response.body());
                        if (onDownloadProgress != null) {
                            onDownloadProgress.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<File> response) {
                        if (onDownloadProgress != null) {
                            onDownloadProgress.onError();
                        }
                    }

                    @Override
                    public void downloadProgress(Progress progress) {

                        //更新通知栏
                        int rate = Math.round(progress.fraction * 100);
                        if (oldRate != rate) {
                            if (builder != null) {
                                builder.setContentTitle("正在下载")
                                        .setContentText(rate + "%")
                                        .setProgress(100, rate, false)
                                        .setWhen(System.currentTimeMillis());
                                Notification notification = builder.build();
                                notification.flags = Notification.FLAG_AUTO_CANCEL |
                                        Notification.FLAG_ONLY_ALERT_ONCE;
                                notificationManager.notify(NOTIFY_ID, notification);
                            }
                            //重新赋值
                            oldRate = rate;
                        }


                        if (onDownloadProgress != null) {
                            onDownloadProgress.onDownloadProgress(rate);
                        }
                    }
                });
    }


    public void cancel() {

        OkGo.getInstance().cancelTag(this);
    }

    /**
     * 直接安装
     */
    private void installApp(File file) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= 24) {
            Uri apkUri = FileProvider.getUriForFile(context, context.getApplicationContext()
                    .getPackageName() + ".provider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");

        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

        context.startActivity(intent);
    }


    /**
     * 检查SD卡权限
     */
    private boolean checkSDCardPermission() {
        if (ActivityCompat.checkSelfPermission(HydraUtilsManager.getInstance().getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            UIHelper.showToast("SD权限被禁止，无法下载文件！");
            return false;
        }

        return true;
    }


    /**
     * 判断文件是否存在  
     *
     * @param file
     * @return
     */
    private boolean fileIsExists(String file) {
        try {
            File f = new File(file);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 创建通知
     */
    private void setUpNotification() {

        notificationManager = (NotificationManager) context.getSystemService(android.content
                .Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(false);
            channel.enableLights(false);

            notificationManager.createNotificationChannel(channel);
        }


        builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle("开始下载")
                .setContentText("正在连接服务器")
                .setSmallIcon(R.drawable.hydra)
                //  .setLargeIcon(AppUpdateUtils.drawableToBitmap(AppUpdateUtils.getAppIcon
                // (DownloadService.this)))
                .setOngoing(true)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());
        notificationManager.notify(NOTIFY_ID, builder.build());
    }


    public void setOnDownloadProgress(OnDownloadProgress onDownloadProgress) {

        this.onDownloadProgress = onDownloadProgress;
    }


    public interface OnDownloadProgress {


        void onSuccess(File file);

        void onError();

        void onDownloadProgress(int progress);

    }

}
