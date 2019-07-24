package com.yeqiu.hydra.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.yeqiu.hydra.net.callback.jsoncallback.JsonCallback;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/4/16 上午10:17
 * @describe：
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
     * 初始化OKGO 设置公参
     *
     * @param application
     */
    public OkGoManager init(Application application) {

        OkGo.getInstance()
                .init(application);
        return getInstance();
    }


    /**
     * 设置公共请求头
     * @param headers
     */
    public OkGoManager setHttpHeaders(HttpHeaders headers) {

        OkGo.getInstance().addCommonHeaders(headers);
        return getInstance();
    }


    /**
     * 获取公共请求头
     */
    public HttpHeaders getCommonHeaders(){

        return OkGo.getInstance().getCommonHeaders();
    }

    /**
     * 获取共参
     */
    public HttpParams getCommonParams(){

        return OkGo.getInstance().getCommonParams();
    }



    /**
     * 添加公参
     *
     * @param
     */
    public OkGoManager setCommonParams(HttpParams params) {

        OkGo.getInstance().addCommonParams(params);

        return getInstance();
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
