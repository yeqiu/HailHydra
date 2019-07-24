package com.yeqiu.hydra.net;

import com.lzy.okgo.model.HttpParams;
import com.yeqiu.hydra.net.bean.BaseBean;
import com.yeqiu.hydra.net.callback.jsoncallback.JsonCallback;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/4/14 下午4:40
 * @describe：网络请求 注：所有在页面中的请求都使用activity做tag 便于页面关闭取消请求
 * @fix：
 */
public class NetManager {

    private static NetManager netWorkManager;

    private NetManager() {

    }

    public static NetManager getInstance() {
        if (netWorkManager == null) {
            netWorkManager = new NetManager();
        }
        return netWorkManager;
    }


    /**
     * 取消单个请求
     *
     * @param tag
     */
    public void cancelRequest(Object tag) {
        OkGoManager.getInstance().cancelRequest(tag);
    }

    /**
     * 取消全部请求
     */
    public void cancelAllRequest() {
        OkGoManager.getInstance().cancelAllRequest();
    }

    /**
     * 发短信
     */
    public void smsSend(String mobile, String action, Object tag,
                        JsonCallback<BaseBean> callback) {
        String url = "https://cxx.xmfenqi.cn/apicommon/sms_send";
        HttpParams params = new HttpParams();
        params.put("mobile", mobile);
        params.put("action", action);

        OkGoManager.getInstance().post(url, params, tag, callback);
    }


    /**
     * 个人中心
     *
     * @param tag
     * @param callback
     */
    public void getUserProfile(Object tag, JsonCallback<String>
            callback) {
        String url = "https://cxx.xmfenqi.cn/api/user/user_profile";
        OkGoManager.getInstance().post(url, tag, callback);
    }


    /**
     * 升级
     *
     * @param tag
     * @param callback
     */
    public void getAppUpgrade(Object tag, JsonCallback<String> callback) {

        String url = "https://cxx.xmfenqi.cn/api/home/app_upgrade";
        OkGoManager.getInstance().post(url, tag, callback);
    }

}
