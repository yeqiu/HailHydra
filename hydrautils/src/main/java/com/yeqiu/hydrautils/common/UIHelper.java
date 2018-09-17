package com.yeqiu.hydrautils.common;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.yeqiu.hydrautils.HydraUtilsManager;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14 上午11:34
 * @describe：防止重复点击的toast 根据时间判断 2秒内不显示相同内容
 * @fix：
 */
public class UIHelper {

    private static String oldMsg;
    private static long time;

    public static void showToast(String msg) {

        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Context context = HydraUtilsManager.getInstance().getContext();
        // 当显示的内容不一样时，即断定为不是同一个Toast
        if (!msg.equals(oldMsg)) {
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
            time = System.currentTimeMillis();
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 2000) {
                Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                toast.setText(msg);
                toast.show();
                time = System.currentTimeMillis();
            }
        }
        oldMsg = msg;
    }

}
