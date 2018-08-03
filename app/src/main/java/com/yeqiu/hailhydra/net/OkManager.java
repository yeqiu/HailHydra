package com.yeqiu.hailhydra.net;

import android.app.Application;

import com.lzy.okgo.model.HttpParams;
import com.yeqiu.hailhydra.net.callback.jsoncallback.JsonCallback;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14 下午4:40
 * @describe：网络请求 注：所有在页面中的请求都使用activity做tag 便于页面关闭取消请求
 * @fix：
 */
public class OkManager {

    private static OkManager netWorkManager;

    private OkManager() {

    }

    public static OkManager getInstance() {
        if (netWorkManager == null) {
            netWorkManager = new OkManager();
        }
        return netWorkManager;
    }

    /**
     * 初始化网络框架
     *
     * @param application
     * @param params
     */
    public void init(Application application, HttpParams params) {
        NetManager.getInstance().init(application, params);
    }



    /**
     * 公参里添加token
     *
     * @param token
     */
    public void addToken(String token, String uid) {
        NetManager.getInstance().addToken(token, uid);
    }

    /**
     * 公参里移除token
     */
    public void removeToken() {

        NetManager.getInstance().removeToken();
    }


    /**
     * 取消单个请求
     *
     * @param tag
     */
    public void cancelRequest(Object tag) {
        NetManager.getInstance().cancelRequest(tag);
    }

    /**
     * 取消全部请求
     */
    public void cancelAllRequest() {
        NetManager.getInstance().cancelAllRequest();
    }


    public void test(Object tag, JsonCallback<String> callback) {

        NetManager.getInstance().post(API.testUrl, tag, callback);

    }


}
