package com.yeqiu.android_tools.net.bean.bean;

import java.io.Serializable;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16 下午2:38
 * @describe：
 * @fix：
 */
public class BaseData implements Serializable {

    /**
     * code : 401
     * message : 用户尚未登录
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
