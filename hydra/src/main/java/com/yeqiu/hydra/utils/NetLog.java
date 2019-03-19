package com.yeqiu.hydra.utils;

import android.text.TextUtils;
import android.util.Log;

import com.yeqiu.hydra.HydraUtilsManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/5/5
 * @describe：网络请求log
 * @fix：
 */
public class NetLog {

    private static boolean openLog = HydraUtilsManager.getInstance().isDebug();;
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
        params = unicodeToString(params);

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


    /**
     * 将unicode转中文
     *
     * @param str
     * @return
     */

    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            String group = matcher.group(2);
            ch = (char) Integer.parseInt(group, 16);
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }



    /**
     * 获取字符串的unicode编码
     * 汉字“木”的Unicode 码点为Ox6728
     *
     * @param s
     * @return \ufeff\u6728  \ufeff控制字符 用来表示「字节次序标记（Byte Order Mark）」不占用宽度
     * 在java中一个char是采用unicode存储的 占用2个字节 比如 汉字木 就是 Ox6728 4bit+4bit+4bit+4bit=2字节
     */

    public static String stringToUnicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            //直接获取字符串的unicode二进制
            byte[] bytes = s.getBytes("unicode");
            //然后将其byte转换成对应的16进制表示即可
            for (int i = 0; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                out.append(str1);
                out.append(str);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }





}