package com.yeqiu.android_tools.net;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/14 下午4:40
 * @describe：获取每个接口的操作
 * @fix：
 *
 * 所有
 *
 *
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





}
