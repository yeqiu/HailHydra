package com.yeqiu.android_tools.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.yeqiu.android_tools.net.bean.callback.JsonCallback;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16 上午10:17
 * @describe：实现网络数据读取和本地缓存数据读取，实现对接网络请求框架 BusinessInerfaceManager
 * @fix：
 */
public class BIManager<T> {



    public void get(String url, JsonCallback<T> callback) {

        OkGo.<T>get(url)
                .tag(this)
                .execute(callback);

    }


    public void get(String url, HttpParams params, JsonCallback<T> callback) {
        OkGo.<T>get(url)
                .tag(this)
                .params(params)
                .execute(callback);
    }


    public void post(String url, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(this)
                .execute(callback);
    }

    public void post(String url, HttpParams params, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(this)
                .params(params)
                .execute(callback);
    }



    /**
     * 初始化OKGO
     * @param application
     */
    public void init(Application application) {
        OkGo.getInstance().init(application);
    }


    public void cancelRequest(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    public void cancelAllRequest(){
        OkGo.getInstance().cancelAll();
    }



}
