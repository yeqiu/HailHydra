package com.yeqiu.hydrautils.utils;

import android.util.Log;

import com.yeqiu.hydrautils.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author ye
 * @date 2017/12/31
 * @desc log 格式打印的工具类
 */
public class PrintUtils {

    private static final String tag = "json";

    public static void printJson(String msg) {
        //正式版不打印
        if (!BuildConfig.DEBUG) {
            return;
        }
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                //最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        printLine(tag, true);
        Log.v(tag, message);
        printLine(tag, false);
    }


    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.v(tag, "═══════════════════华丽的分割线 ~开始═══════════════════");
        } else {
            Log.v(tag, "═══════════════════华丽的分割线 ~结束═══════════════════");

        }
    }


}
