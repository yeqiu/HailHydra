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
package com.yeqiu.hailhaydra.net.callback.jsoncallback;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydra.utils.NetLog;
import com.yeqiu.hydra.utils.UIHelper;

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
                switch (next) {
                    case "AuthToken":
                        continue;
                    case "Q-Unique":
                        continue;
                    case "Q-DeviceModel":
                        continue;
                    case "Q-DeviceName":
                        continue;
                    case "Q-DeviceId":
                        continue;
                    case "Q-SystemType":
                        continue;
                    case "Q-SystemVersion":
                        continue;
                    case "Q-AppChannelFrom":
                        continue;
                    case "Q-AppVersion":
                        continue;
                    case "Q-Mac":
                        continue;
                    case "Q-WifiMac":
                        continue;
                    case "Q-AppId":
                        continue;
                    case "Q-User-Agent":
                        continue;
                    case "Q-Imei":
                        continue;
                    case "Q-app_version":
                        continue;
                    case "Q-app_type":
                        continue;
                    case "Q-CacheKey":
                        continue;
                    case "app_version":
                        continue;
                    case "app_type":
                        continue;

                    default:
                        break;
                }
                map.put(next, strings1.get(0));
            }
        }

        HttpHeaders headers = request.getHeaders();
        String authToken = headers.get("AuthToken");
        map.put("AuthToken", authToken);
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
            onNetError(-1004, "数据异常");
        } else {
            onNetSuccess(data);
        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        //okgo返回错误，可能是连接错误，可能是数据解析错误
        Throwable exception = response.getException();

        if (exception instanceof ConnectException) {
            onNetError(-1000, "网络异常");
        } else if (exception instanceof UnknownHostException) {
            onNetError(-1001, "网络异常");
        } else if (exception instanceof SocketTimeoutException) {
            onNetError(-1002, "网络连接超时");
        } else if (exception instanceof StorageException) {
            onNetError(-1003, "sd卡不存在或者没有权限");
        } else if (exception instanceof JsonSyntaxException) {
            onNetError(-1004, "数据异常");
        } else if (exception instanceof HttpException) {
            onNetError(404, "网络连接失败");
        } else {
            //其他异常
            onNetError(-1005, "其他异常，请稍后重试");
        }
    }

    /**
     * 网络请求成功回调，保证data!=null
     *
     * @param data
     */
    public abstract void onNetSuccess(T data);


    /**
     * 网络请求失败回调
     *
     * @param msg
     * @param code -1000 无网络
     *             -1001 未知主机
     *             -1002 网络超时
     *             -1003 sd不存在或者无储存权限
     *             -1004 数据有误
     *             -1005 其他异常
     *             404 找不到资源
     */
    public void onNetError(int code, String msg) {

        if (showErrorMsg()) {
            UIHelper.showToast(msg);
        }

    }



    /**
     * 是否显示错误信息吐司
     *
     * @return
     */
    protected boolean showErrorMsg() {

        return true;
    }

}
