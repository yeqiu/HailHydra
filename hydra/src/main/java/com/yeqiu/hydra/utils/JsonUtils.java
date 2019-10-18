package com.yeqiu.hydra.utils;


import com.google.gson.Gson;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class JsonUtils {

    private static Gson gson;

    public static Gson getGson() {

        if (gson == null) {
            gson = new Gson();
        }

        return gson;
    }


    /**
     * json转对象
     *
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonTobean(String result, Class<T> clazz) {

        T t = getGson().fromJson(result, clazz);
        return t;
    }

    /**
     * 对象转json
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String beanTojson(T t) {

        return getGson().toJson(t);
    }



}
