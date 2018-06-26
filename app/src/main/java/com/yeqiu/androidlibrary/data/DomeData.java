package com.yeqiu.androidlibrary.data;

import java.io.Serializable;

/**
 * @author ye
 * @date 2018/4/2
 * @desc
 */
public class DomeData implements Serializable {

    private String title ;
    private Class activity ;

    public DomeData(String title, Class activity) {
        this.title = title;
        this.activity = activity;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
