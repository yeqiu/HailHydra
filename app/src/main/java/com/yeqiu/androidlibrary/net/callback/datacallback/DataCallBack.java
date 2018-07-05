package com.yeqiu.androidlibrary.net.callback.datacallback;

import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.model.Response;
import com.yeqiu.androidlibrary.net.callback.jsoncallback.JsonCallback;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/20
 * @describe：
 * @fix：
 */
public abstract class DataCallBack<T> extends JsonCallback<T> {
    @Override
    public void onSuccess(Response<T> response) {
        T data = response.body();
        if (data == null) {
            onError(0, "数据异常");
        } else {
            onSuccess(data);
        }


    }


    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        onError(-1, "网络异常");


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
     * @param code 0:成功回调的数据=null, -1 网络请求错误
     * @return
     * @parm msg
     */
    public void onError(int code, String msg) {

        if (!TextUtils.isEmpty(msg)) {
            Log.i("test", "DataCallBack:onError,msg =  "+msg);
        }


    }


}
