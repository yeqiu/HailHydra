package com.yeqiu.hydra.net.bean;

import java.io.Serializable;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/4/16 下午2:38
 * @describe：data为null
 * @fix：
 */
public class BaseBean<T> implements Serializable {

    /**
     * code : 0
     * message : ""
     */

    private int code;
    private String message;
    private T data;

    private boolean isOk;


    public boolean isOk() {

        return getCode() == 0;
    }


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
