package com.yeqiu.hydra.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;

import com.yeqiu.hydra.HydraUtilsManager;

import java.io.DataOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import androidx.core.app.NotificationManagerCompat;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/6
 * @describe：获取app的信息
 * @fix：
 */
public class APPInfoUtil {


    /**
     * 获取app名
     *
     * @return
     */
    public static String getAppName() {

        Context context = HydraUtilsManager.getInstance().getContext();

        try {
            PackageManager e = context.getPackageManager();
            PackageInfo packageInfo = e.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
            return "unKnown";

        }
    }


    /**
     * 获取渠道名
     *
     * @return
     */
    public static String getChannelName() {


        Context context = HydraUtilsManager.getInstance().getContext();
        String channelName = "unKnown";
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.
                        getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = String.valueOf(applicationInfo.metaData.get("UMENG_CHANNEL"));
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

            return "unKnown";
        }
        return channelName;
    }

    /**
     * 获取版本名
     */
    public static String getVersionName() {

        Context context = HydraUtilsManager.getInstance().getContext();

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packInfo != null) {
                return packInfo.versionName;
            } else {
                return "unKnown";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "unKnown";
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        Context context = HydraUtilsManager.getInstance().getContext();
        int versionCodeInt = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            versionCodeInt = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCodeInt;
    }


    /**
     * 获取应用图标
     *
     * @return
     */
    public static Drawable getAppIcon() {
        Context context = HydraUtilsManager.getInstance().getContext();
        PackageManager pm = context.getPackageManager();
        Drawable appIcon = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(getPackageName(), 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }

    /**
     * 获取应用图标
     *
     * @return
     */
    public static boolean isNotificationOpen() {
        Context context = HydraUtilsManager.getInstance().getContext();
        return NotificationManagerCompat.from(context).areNotificationsEnabled();

    }


    /**
     * 获取包名
     *
     * @return
     */
    public static String getPackageName() {
        Context context = HydraUtilsManager.getInstance().getContext();
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 获取应用第一次安装日期
     *
     * @return
     */
    public static long getFirstInstallTime() {
        Context context = HydraUtilsManager.getInstance().getContext();
        long lastUpdateTime = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName()
                    , 0);
            lastUpdateTime = packageInfo.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return lastUpdateTime;
    }

    /**
     * 获取最近更新日期
     *
     * @return
     */
    public static long getAppLastUpdateTime() {
        Context context = HydraUtilsManager.getInstance().getContext();
        long lastUpdateTime = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName()
                    , 0);
            lastUpdateTime = packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return lastUpdateTime;
    }


    /**
     * 获取应用大小
     *
     * @return
     */
    public static long getAppSize() {
        Context context = HydraUtilsManager.getInstance().getContext();
        long appSize = 0;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (getPackageName(), 0);
            appSize = new File(applicationInfo.sourceDir).length();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appSize;
    }

    /**
     * 提取应用apk文件
     *
     * @return
     */
    public static String getAppApk() {
        Context context = HydraUtilsManager.getInstance().getContext();
        String sourceDir = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (getPackageName(), 0);
            sourceDir = applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sourceDir;
    }


    /**
     * 获取应用兼容sdk
     *
     * @return
     */
    public static int getAppTargetSdkVersion() {
        Context context = HydraUtilsManager.getInstance().getContext();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName()
                    , 0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            return applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取应用uid
     *
     * @return
     */
    public static int getAppUid() {

        Context context = HydraUtilsManager.getInstance().getContext();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName()
                    , 0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            return applicationInfo.uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获得root权限
     *
     * @return
     */
    public static boolean getRootPermission() {
        Context context = HydraUtilsManager.getInstance().getContext();
        String packageCodePath = context.getPackageCodePath();
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + packageCodePath;
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * 获取应用的所有权限
     *
     * @return
     */
    public static String[] getAppPermissions() {
        Context context = HydraUtilsManager.getInstance().getContext();
        String[] requestedPermissions = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_PERMISSIONS);
            requestedPermissions = info.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return requestedPermissions;
    }


    /**
     * 应用是否指定安装
     *
     * @param packageName
     * @return
     */
    public static boolean isInstalled(String packageName) {
        Context context = HydraUtilsManager.getInstance().getContext();
        boolean installed = false;
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        List<ApplicationInfo> installedApplications = context.getPackageManager()
                .getInstalledApplications(0);
        for (ApplicationInfo in : installedApplications) {
            if (packageName.equals(in.packageName)) {
                installed = true;
                break;
            } else {
                installed = false;
            }
        }
        return installed;
    }


    /**
     * 结束进程
     */
    public static void killProcesses() {
        Context context = HydraUtilsManager.getInstance().getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        String packageName;
        try {
            if (!getPackageName().contains(":")) {
                packageName = getPackageName();
            } else {
                packageName = getPackageName().split(":")[0];
            }
            activityManager.killBackgroundProcesses(packageName);
            Method forceStopPackage = activityManager.getClass().getDeclaredMethod
                    ("forceStopPackage", String.class);
            forceStopPackage.setAccessible(true);
            forceStopPackage.invoke(activityManager, packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开指定应用
     *
     * @param packagename
     */
    public static void runApp(String packagename) {
        Context context = HydraUtilsManager.getInstance().getContext();
        context.startActivity(new Intent(context.getPackageManager().getLaunchIntentForPackage
                (packagename)));
    }

    /**
     * 检查权限
     *
     * @param context
     * @param permission 例如 Manifest.permission.READ_PHONE_STATE
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                result = rest == PackageManager.PERMISSION_GRANTED;
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager
                    .PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }


    /**
     * 获取包名最后一个节点名
     *
     * @return
     */
    public static String getPackageNameLast() {

        String packageName = HydraUtilsManager.getInstance().getContext().getPackageName();

        String[] split = packageName.split("\\.");

        if (split.length > 0) {
            String s = split[split.length - 1];
            return s;

        }

        return "";
    }


    /**
     * 重启应用
     */
    public static void restart() {


        System.gc();

        Context context = HydraUtilsManager.getInstance().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
