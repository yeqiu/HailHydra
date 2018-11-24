/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yeqiu.hydrautils.net.callback.jsoncallback;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydrautils.utils.LogUtils;
import com.yeqiu.hydrautils.utils.NetLog;
import com.yeqiu.hydrautils.utils.UIHelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Response;


/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16
 * @describe：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * @fix：
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    public JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现


        //  NetLog.logUrl(request.getUrl(), request.getParams().toString());

        //log 参数
        HttpParams params = request.getParams();
        Map<String, String> map = new HashMap<>();
        LinkedHashMap<String, List<String>> urlParamsMap = params.urlParamsMap;
        Set<String> strings = urlParamsMap.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            List<String> strings1 = urlParamsMap.get(next);
            if (strings1 != null && strings1.size() > 0) {
                map.put(next, strings1.get(0));
            }
        }

        NetLog.logUrl(request.getUrl(), new Gson().toJson(map));

    }


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     */
    @Override
    public T convertResponse(Response response) throws Throwable {

        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);

    }


    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        T data = response.body();
        if (data == null) {
            onError(0, "数据异常");
        } else {
            onSuccess(data);
        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        //okgo返回错误，可能是连接错误，可能是数据解析错误

        Throwable exception = response.getException();

        LogUtils.e(exception.getMessage());

        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            onError(-1000, "网络连接失败");
        } else if (exception instanceof SocketTimeoutException) {
            onError(-1001, "网络连接超时");
        } else if (exception instanceof HttpException) {
            onError(404, "网络连接失败");
        } else if (exception instanceof StorageException) {
            onError(-1002, "sd卡不存在或者没有权限");
        } else if (exception instanceof IllegalStateException) {
            String message = exception.getMessage();
            //位置错误
            if (TextUtils.isEmpty(message)) {
                message = "网络异常";
            }
            onError(-1003, message);
        }


    }

    /**
     * 网络请求成功回调，保证data!=null
     *
     * @param data
     */
    public abstract void onSuccess(T data);


    /**
     * 网络请求失败回调
     *
     * @param msg
     * @param code 0:请求成功 数据转换异常
     *             1000:网络连接失败 无网络
     *             1001:请求超时
     *             404:服务器返回404
     *             1002:sd卡不存在或者没有权限
     *             1003:其他错误
     */
    public void onError(int code, String msg) {

        if (!TextUtils.isEmpty(msg)) {
            UIHelper.showToast( msg);
        }
    }


}
