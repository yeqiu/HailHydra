package com.yeqiu.hailhaydra.net;

import android.app.Application;
import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.yeqiu.hailhaydra.net.callback.jsoncallback.JsonCallback;


/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16 上午10:17
 * @describe：实现网络数据读取和本地缓存数据读取，实现对接网络请求框架
 * @fix：
 */
public class OkGoManager<T> {


    private static OkGoManager instance;

    private OkGoManager() {

    }

    public static OkGoManager getInstance() {
        if (instance == null) {
            instance = new OkGoManager();
        }
        return instance;
    }


    /**
     * 初始化OKGO
     *
     * @param application
     */
    public void init(Application application) {

        OkGo.getInstance()
                .init(application);

    }


    /**
     * 初始化OKGO 设置公参
     *
     * @param application
     * @param params
     */
    public void init(Application application, @NonNull HttpParams params) {

        OkGo.getInstance()
                .init(application)
                .addCommonParams(params);

    }

    /**
     * 设置请求头
     *
     * @param headers
     */
    public void setHttpHeaders(HttpHeaders headers) {

        OkGo.getInstance().addCommonHeaders(headers);

    }

    /**
     * 获取请求头
     *
     * @return
     */
    public HttpHeaders getHttpHeaders() {

        return OkGo.getInstance().getCommonHeaders();
    }


    /**
     * 获取公参
     *
     * @return
     */
    public HttpParams getHttpParams() {

        return OkGo.getInstance().getCommonParams();
    }


    /**
     * 添加公参
     *
     * @param httpParams
     */
    public void setHttpParams(HttpParams httpParams) {

        OkGo.getInstance().addCommonParams(httpParams);
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
