package com.yeqiu.hailhydra.utils.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/6/14
 * @describe：
 * @fix：
 */
public class EventBusUtils {


    /**
     * 注册事件
     *
     * @param object
     */
    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }

    /**
     * 烦注册事件
     *
     * @param object
     */
    public static void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }


}
