package com.yeqiu.hailhydra.net;

import android.text.TextUtils;
import android.util.Log;

import com.yeqiu.hailhydra.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/5/5
 * @describe：网络请求log
 * @fix：
 */
public class NetLog {

    private static boolean openLog = BuildConfig.DEBUG;
    private static String baseTab = "netlog_";
    private static String tagJson = baseTab + "json";
    private static String tagUrl = baseTab + "url";


    /**
     * 打印网络请求回来的json和接口
     *
     * @param url
     * @param json
     */
    public static void logJson(String url, String json) {
        if (!openLog) {
            return;
        }

        String message;
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(4);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(4);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }
        Log.v(tagJson, "<=====");
        //printLine(tagJson, true);
        String msg = "===== " + url + " =====";
        Log.v(tagJson, msg);
        Log.v(tagJson, message);
       // printLine(tagJson, false);
        Log.v(tagJson, "  ");


    }


    /**
     * 打印请求前的url和参数
     *
     * @param url
     * @param params
     */
    public static void logUrl(String url, String params) {
        if (!openLog) {
            return;
        }
        if (!TextUtils.isEmpty(url)) {
            Log.v(tagUrl, "=====>");
            Log.v(tagUrl, "本次访问接口 " + url);
        }

        if (!TextUtils.isEmpty(params)) {
            Log.v(tagUrl, "本次访问参数 " + params);
        }

        Log.v(tagJson, "  ");

    }


    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.v(tag, "═══════════════════华丽的分割线 ~开始═══════════════════");
        } else {
            Log.v(tag, "═══════════════════华丽的分割线 ~结束═══════════════════");

        }
    }





}
