package com.yeqiu.hailhydra.net.bean;

import java.io.Serializable;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16 下午2:38
 * @describe：data为null
 * @fix：
 */
public class BaseBean implements Serializable {

    /**
     * code : 0
     * message : ""
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
