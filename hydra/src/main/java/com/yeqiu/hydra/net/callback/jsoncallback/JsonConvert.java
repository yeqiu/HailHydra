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
package com.yeqiu.hydra.net.callback.jsoncallback;

import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;
import com.lzy.okgo.utils.IOUtils;
import com.yeqiu.hydra.utils.NetLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public class JsonConvert<T> implements Converter<T> {

    private Type type;
    private Class<T> clazz;

    public JsonConvert() {
    }

    public JsonConvert(Type type) {
        this.type = type;
    }

    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {


        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                return parseClass(response, clazz);
            }
        }


        if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {

            return parseType(response, type);
        }
    }

    private T parseClass(Response response, Class<?> rawType) throws Exception {


        response = logResponse(response);

        if (rawType == null){
            return null;
        }
        ResponseBody body = response.body();
        if (body == null){
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());

        if (rawType == String.class) {
            //noinspection unchecked
            return (T) body.string();
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(body.string());
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(body.string());
        } else {
            T t = Convert.fromJson(jsonReader, rawType);
            response.close();

            if (t == null) {
                Log.e("log", "网络请求的数据是null");
                throw new NullPointerException("网络请求的数据是null");
            }
            return t;
        }


    }


    private T parseType(Response response, Type type) throws Exception {
        if (type == null){
            return null;
        }
        ResponseBody body = response.body();
        if (body == null){
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());

        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        T t = Convert.fromJson(jsonReader, type);
        response.close();
        return t;
    }


    /**
     * 打印返回的json数据
     * 手动构建Response并返回 ，直接打印会释放Response
     *
     * @param response
     * @return
     */
    private Response logResponse(Response response) {

        Response.Builder builder = response.newBuilder();
        Response clone = builder.build();
        ResponseBody responseBody = clone.body();

        try {
            String url = clone.request().url().toString();
            byte[] bytes = IOUtils.toByteArray(responseBody.byteStream());
            MediaType contentType = responseBody.contentType();
            String json = new String(bytes, getCharset(contentType));
            NetLog.logJson(url, json);

            responseBody = ResponseBody.create(responseBody.contentType(), bytes);
            return response.newBuilder().body(responseBody).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }



    private static Charset getCharset(MediaType contentType) {
        Charset UTF8 = Charset.forName("UTF-8");
        Charset charset = contentType != null ? contentType.charset(UTF8) : UTF8;
        if (charset == null) {
            charset = UTF8;
        }
        return charset;
    }


}
