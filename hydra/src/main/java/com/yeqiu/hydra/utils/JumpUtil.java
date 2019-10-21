package com.yeqiu.hydra.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;

import com.yeqiu.hydra.HydraUtilsManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-10-14
 * @describe：activity启动工具
 * @fix：
 */
public class JumpUtil {

    private static final int REQUEST_CODE = 100;


    /**
     * 通过Intent跳转
     *
     * @param intent
     */
    public static void jumpTo(Intent intent) {

        if (intent.getFlags() != Intent.FLAG_ACTIVITY_NEW_TASK) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        Context context = HydraUtilsManager.getInstance().getContext();
        context.startActivity(intent);
    }

    /**
     * 传入目标页面跳转
     *
     * @param destination
     */
    public static void jumpTo(Class<? extends Activity> destination) {

        jumpTo(getIntent(destination));

    }

    /**
     * 传入目标页面并携带参数跳转
     *
     * @param destination
     * @param params
     */
    public static void jumpTo(Class<? extends Activity> destination, Object... params) {

        jumpTo(getIntent(destination, params));

    }


    /**
     * 通过Intent跳转并返回结果
     *
     * @param origin
     * @param intent
     */
    public static void jumpToForResult(Activity origin, Intent intent) {

        if (intent.getFlags() != Intent.FLAG_ACTIVITY_NEW_TASK) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        origin.startActivityForResult(intent, REQUEST_CODE);

    }


    /**
     * 传入目标页面跳转并返回结果
     *
     * @param origin
     * @param destination
     */
    public static void jumpToForResult(Activity origin, Class<? extends Activity> destination) {


        jumpToForResult(origin, getIntent(destination));

    }


    /**
     * 传入目标页面 携带参数 跳转并返回结果
     *
     * @param origin
     * @param destination
     * @param params
     */
    public static void jumpToForResult(Activity origin, Class<? extends Activity> destination,
                                       Object... params) {

        jumpToForResult(origin, getIntent(destination, params));

    }


    /**
     * 打开手机默认浏览器
     */
    public static void jumpToBrowserActivity(String url) {

        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            jumpTo(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转拨号页面
     *
     * @param number
     */
    public static void jumpToDialActivity(String number) {

        if (TextUtils.isEmpty(number)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        jumpTo(intent);
    }


    /**
     * 跳转到app的设置页面(包括通知管理、应用权限等)
     */
    public static void goSetting() {

        String SETTINGS_ACTION = "android.settings.APPLICATION_DETAILS_SETTINGS";
        Context context = HydraUtilsManager.getInstance().getContext();
        Intent intent = new Intent()
                .setAction(SETTINGS_ACTION)
                .setData(Uri.fromParts("package",
                        context.getApplicationContext().getPackageName(), null));
        jumpTo(intent);
    }

    /**
     * 跳转系统的蓝牙设置界面
     */
    public static void goBluetoothSetting() {
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        jumpTo(intent);
    }

    /**
     * 跳转到无限网络界面
     */
    public static void goWifiSetting() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        jumpTo(intent);
    }

    /**
     * 跳转到移动网络设置界面
     */
    public static void goNetworkSetting() {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        jumpTo(intent);
    }

    /**
     * 跳转位置服务界面【管理已安装的应用程序】
     */
    public static void goLocationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        jumpTo(intent);
    }


    private static Intent getIntent(Class<? extends Activity> destination) {
        Context context = HydraUtilsManager.getInstance().getContext();
        Intent intent = new Intent(context, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    private static Intent getIntent(Class<? extends Activity> destination, Object... params) {

        Intent intent = getIntent(destination);

        if (params == null) {
            return intent;
        }

        if (params.length % 2 != 0) {
            //key和value的数量不匹配
            throw new IllegalArgumentException("key和value的数量不匹配");
        }
        for (int i = 0; i < params.length / 2; i++) {
            putValueToIntent(intent, (String) params[i * 2], params[i * 2 + 1]);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 往bundle里存值
     *
     * @param intent
     * @param key
     * @param value
     */
    private static void putValueToIntent(Intent intent, String key, Object value) {

        if (TextUtils.isEmpty(key) || value == null) {
            return;
        }

        if (value instanceof String) {
            intent.putExtra(key, (String) value);

            return;
        }

        if (value instanceof Integer) {
            intent.putExtra(key, (int) value);

            return;
        }


        if (value instanceof Boolean) {
            intent.putExtra(key, (boolean) value);
            return;
        }

        if (value instanceof Parcelable) {
            intent.putExtra(key, (Parcelable) value);
            return;
        }

        if (value instanceof Serializable) {
            intent.putExtra(key, (Parcelable) value);
            return;
        }


        if (value instanceof Long) {
            intent.putExtra(key, (Long) value);
            return;
        }

        if (value instanceof Float) {
            intent.putExtra(key, (float) value);
            return;
        }

        if (value instanceof List) {
            List list = (List) value;
            if (list.size() == 0) {
                return;
            }

            if (list.get(0) instanceof String) {
                intent.putExtra(key, (ArrayList<String>) value);
                return;
            }

            if (list.get(0) instanceof Parcelable) {
                intent.putExtra(key, (ArrayList<? extends Parcelable>) value);
                return;
            }


            if (list.get(0) instanceof Integer) {
                intent.putExtra(key, (ArrayList<Integer>) value);
                return;
            }


        }

        throw new IllegalArgumentException("value是无法识别的数据类型");
    }

}
