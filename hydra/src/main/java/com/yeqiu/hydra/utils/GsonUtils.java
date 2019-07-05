package com.yeqiu.hydra.utils;


import com.google.gson.Gson;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class GsonUtils {

    public static <T> T jsonTobean(String result , Class<T> clazz){

        T t = getGson().fromJson(result, clazz);
        return t;
    }

    public static <T> String beanTojson(T t){

        return getGson().toJson(t);
    }

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null){
            gson = new Gson();
        }

        return gson;
    }

}
