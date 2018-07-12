package com.yeqiu.androidlibrary.utils.eventbus;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/6/8
 * @describe：
 * @fix：
 */
public class EventBusData {


    public int eventType;

    public String eventStringData;

    public boolean eventBooleanData;


    public EventBusData(int eventType) {
        this.eventType = eventType;
    }

    public EventBusData(int eventType, String eventStringData) {
        this.eventType = eventType;
        this.eventStringData = eventStringData;
    }

    public EventBusData(int eventType, boolean eventBooleanData) {
        this.eventType = eventType;
        this.eventBooleanData = eventBooleanData;
    }


    public int getEventType() {
        return eventType;
    }

    public String getEventStringData() {
        return eventStringData == null ? "" : eventStringData;
    }

    public boolean isEventBooleanData() {
        return eventBooleanData;
    }
}
