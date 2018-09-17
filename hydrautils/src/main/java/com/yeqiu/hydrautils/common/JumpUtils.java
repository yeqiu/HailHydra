package com.yeqiu.hydrautils.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.yeqiu.hydrautils.HydraUtilsManager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/17
 * @describe：activity fragment跳转工具类
 * @fix：
 */
public class JumpUtils {


    private static JumpUtils instance;

    private JumpUtils() {
    }


    public static JumpUtils getInstance() {

        if (instance == null) {
            synchronized (JumpUtils.class) {
                if (instance == null) {
                    instance = new JumpUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 打开手机默认浏览器
     */
    public void jumpToBrowserActivity(String url) {

        Context context = HydraUtilsManager.getInstance().getContext();
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转拨号页面
     *
     * @param number
     */
    public void jumpToDialActivity(String number) {

        if (TextUtils.isEmpty(number)) {
            return;
        }
        Context context = HydraUtilsManager.getInstance().getContext();
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        context.startActivity(dialIntent);
    }


    /**
     * 跳转到指定acitvity
     *
     * @param clazz
     */
    public void jumpToActivityByClass(Class<? extends Activity> clazz) {
        Context context = HydraUtilsManager.getInstance().getContext();
        Intent intent = new Intent(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
