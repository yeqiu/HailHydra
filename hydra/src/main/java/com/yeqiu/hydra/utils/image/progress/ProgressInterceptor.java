package com.yeqiu.hydra.utils.image.progress;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/23
 * @describe：
 * @fix：
 */
public class ProgressInterceptor implements Interceptor {

    public static final Map<String, ProgressListener> LISTENER_MAP = new HashMap<>();

    public static void addListener(String url, ProgressListener listener) {
        LISTENER_MAP.put(url, listener);
    }


    public static void removeListener(String url) {
        LISTENER_MAP.remove(url);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String url = request.url().toString();
        ResponseBody body = response.body();
        Response newResponse =
                response.newBuilder().body(new ProgressResponseBody(url, body)).build();
        return newResponse;
    }
}