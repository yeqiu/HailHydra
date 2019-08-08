package com.yeqiu.hydra.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

import com.yeqiu.hydra.HydraUtilsManager;

import java.io.File;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class PhoneInfoUtil {

    /**
     * 获取手机系统版本号
     *
     * @return
     */
    public static int getSDKVersion() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            sdkVersion = 0;
        }
        return sdkVersion;
    }


    /**
     * 获取手机型号
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }


    /**
     * 获取手机imei串号
     * 需要权限  <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneImei() {

        try {
            Context context = HydraUtilsManager.getInstance().getContext();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                return tm.getDeviceId();
            }

        } catch (Exception e) {

        }

        return "";
    }


    /**
     * 获取手机号
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneNum() {



        try {
            Context context = HydraUtilsManager.getInstance().getContext();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm == null) {
                return null;
            }
            return tm.getLine1Number();

        } catch (Exception e) {

        }

        return "";
    }


    /**
     * 判断sd卡是否挂载
     */
    public static boolean hasSd() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取sd卡剩余空间的大小
     */
    public static long getSdFreeSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        long blockSize = sf.getBlockSize();
        long freeBlocks = sf.getAvailableBlocks();
        return (freeBlocks * blockSize) / 1024 / 1024;
    }

    /**
     * 获取sd卡空间的总大小
     */
    public static long getSdSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        long blockSize = sf.getBlockSize();
        long allBlocks = sf.getBlockCount();
        return (allBlocks * blockSize) / 1024 / 1024;
    }

    /**
     * 判断是否是平板
     */
    public static boolean isTablet() {

        Context context = HydraUtilsManager.getInstance().getContext();
        return (context.getResources().getConfiguration().screenLayout & Configuration
                .SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    /**
     * Gps是否打开
     *
     * @return
     */
    public static boolean isGpsEnabled() {

        Context context = HydraUtilsManager.getInstance().getContext();
        LocationManager locationManager = ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = locationManager.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }


    /**
     * 是否是手机
     *
     * @return
     */
    public static boolean isPhone() {

        Context context = HydraUtilsManager.getInstance().getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        return tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }


    /**
     * 设置屏幕为横屏
     * <p>还有一种就是在Activity中加属性android:screenOrientation="landscape"</p>
     * <p>不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次</p>
     * <p>设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次</p>
     * <p>设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"（4.0以上必须带最后一个参数）时
     * 切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法</p>
     *
     * @param activity activity
     */
    public static void setLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 设置屏幕为竖屏
     *
     * @param activity activity
     */
    public static void setPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 判断是否横屏
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isLandscape() {

        Context context = HydraUtilsManager.getInstance().getContext();
        return context.getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_LANDSCAPE;
    }

    /**
     * 判断是否竖屏
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isPortrait() {

        Context context = HydraUtilsManager.getInstance().getContext();
        return context.getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_PORTRAIT;
    }

    /**
     * 判断是否锁屏
     *
     * @return {@code true}:
     */
    public static boolean isScreenLock() {

        Context context = HydraUtilsManager.getInstance().getContext();
        KeyguardManager km = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }


    /**
     * 获取当前使用的网络类型
     *
     * @return <p>
     * 0 = "无网络";
     * 1 = "网络断开";
     * 2 = "wifi";
     * 3 = "wifi";
     * 4 = "移动数据";
     * </p>
     */
    public static int getNetworkType() {

        Context context = HydraUtilsManager.getInstance().getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        return tm.getNetworkType();
    }

    /**
     * 获取运营商名称
     *
     * @return
     */
    public static String getSimOperatorName() {

        Context context = HydraUtilsManager.getInstance().getContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        return tm.getSimOperatorName();
    }


    /**
     * 获取设备厂商，如Xiaomi
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

}
