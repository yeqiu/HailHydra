package com.yeqiu.hailhaydra.bean.Model;

import java.io.Serializable;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/13
 * @describe：
 * @fix：
 */
public class ModelHaydraItem  implements Serializable{

    private String name;

    private int image;

    private Class activity;

    public ModelHaydraItem(String name, int image, Class activity) {
        this.name = name;
        this.image = image;
        this.activity = activity;
    }

    public ModelHaydraItem(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
