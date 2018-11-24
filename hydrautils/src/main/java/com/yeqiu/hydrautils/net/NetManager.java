package com.yeqiu.hydrautils.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.yeqiu.hydrautils.net.callback.jsoncallback.JsonCallback;


/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16 上午10:17
 * @describe：实现网络数据读取和本地缓存数据读取，实现对接网络请求框架
 * @fix：
 */
public class NetManager<T> {


    private static NetManager instance;

    private NetManager() {

    }

    public static NetManager getInstance() {
        if (instance == null) {
            instance = new NetManager();
        }
        return instance;
    }


    /**
     * 初始化OKGO 设置公参
     *
     * @param application
     * @param params
     */
    public void init(Application application, HttpParams params) {

        if (params != null) {
            OkGo.getInstance()
                    .init(application)
                    .addCommonParams(params);
        } else {
            OkGo.getInstance()
                    .init(application);
        }

    }

    public void setHttpHeaders(HttpHeaders headers) {


        OkGo.getInstance().addCommonHeaders(headers);

    }


    /**
     * 公参里添加token
     *
     * @param token
     */
    public void addToken(String token) {


        HttpHeaders commonHeaders = OkGo.getInstance().getCommonHeaders();

        commonHeaders.put("AuthToken", token);
    }

    /**
     * 公参里移除token
     */
    public void removeToken() {

        HttpHeaders commonHeaders = OkGo.getInstance().getCommonHeaders();

        if (commonHeaders != null) {
            commonHeaders.remove("AuthToken");
        }

    }


    /**
     * 取消单个请求
     *
     * @param tag
     */
    public void cancelRequest(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    /**
     * 取消全部请求
     */
    public void cancelAllRequest() {
        OkGo.getInstance().cancelAll();
    }

    /**
     * 无参数的get请求
     *
     * @param url
     * @param callback
     * @param tag
     */
    public void get(String url, Object tag, JsonCallback<T> callback) {

        OkGo.<T>get(url)
                .tag(tag)
                .execute(callback);

    }

    /**
     * 有参数的get请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void get(String url, Object tag, HttpParams params, JsonCallback<T> callback) {
        OkGo.<T>get(url)
                .tag(tag)
                .params(params)
                .execute(callback);
    }


    /**
     * 无参数的post请求
     *
     * @param url
     * @param callback
     */
    public void post(String url, Object tag, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .execute(callback);
    }

    /**
     * 有参数的post请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void post(String url, HttpParams params, Object tag, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .params(params)
                .execute(callback);
    }


}
