package com.yeqiu.hailhydra.utils;

import com.google.gson.Gson;
import com.yeqiu.hailhydra.BuildConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/6/13
 * @describe：制作json的工具类
 * @fix：
 */
public class JsonMarker {
    private String[] keys;
    private Object[] values;

    private JsonMarker() {
    }

    public static JsonMarker build() {

        return new JsonMarker();
    }


    public JsonMarker setKeys(String... key) {
        this.keys = key;

        return this;
    }

    public JsonMarker setValues(Object... values) {
        this.values = values;

        return this;
    }


    public String makeJson() {

        if (keys == null || values == null || keys.length != values.length) {
            //key或者value 为null 两者的长度不一致
            if (BuildConfig.DEBUG) {
                LogUtils.e("key或者value 为null 两者的长度不一致");
                throw new IllegalArgumentException("key或者value为null 两者的长度不一致");
            } else {
                return "";
            }
        }

        Map<Object, Object> map = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Object value = values[i];
            map.put(key, value);
        }

        return new Gson().toJson(map);

    }


}
