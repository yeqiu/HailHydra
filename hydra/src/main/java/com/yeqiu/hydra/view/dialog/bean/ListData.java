package com.yeqiu.hydra.view.dialog.bean;

import com.yeqiu.hydrautils.R;

import java.io.Serializable;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/23
 * @describe：
 * @fix：
 */
public class ListData implements Serializable {

    private String title;
    private String icon;
    private int iconId = R.drawable.hydra;


    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon == null ? "" : icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public ListData(String title) {
        this.title = title;
    }

    public ListData(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }


    public ListData(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }
}
