package com.yeqiu.hailhaydra.net;

import com.yeqiu.hailhaydra.net.callback.jsoncallback.JsonCallback;

/**
 * @project：Xbzd
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
     * 站点列表
     */
    public void getMachineList(Object tag, JsonCallback<String> callback) {

        OkGoManager.getInstance().post(API.machine_list, tag, callback);
    }



}
