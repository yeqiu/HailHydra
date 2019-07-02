package com.yeqiu.hydra.ui;

import com.yeqiu.hydrautils.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：输入框工具配置
 * @fix：
 */
public class UiConfig {

    private static UiConfig instance;



    private UiConfig() {
        instance = this;
    }

    public static synchronized UiConfig getInstance() {
        if (instance == null) {
            instance = new UiConfig();
        }
        return instance;
    }


    /**
     * 默认view的颜色
     */
    private int defColor = R.color.color_b9b9b9;
    /**
     * 达到指定条件后view的颜色
     */
    private int limitcolor = R.color.color_0d95ff;
    /**
     * 未达到指定条件view是否可以点击
     */
    private boolean defClickable = false;
    /**
     * 指定条件 字符串的数量
     */
    private int limitNumber = 11;

    /**
     * 默认view的背景
     */
    private int defBg = R.drawable.shape_40_0_0_e5e5e5;
    /**
     * 达到指定条件后view的背景
     */
    private int limitBg = R.drawable.shape_40_0_0_0d95ff;


    /**
     * 图片加载占位图
     */
    private int imgPlaceholder = -1 ;
    /**
     * 图片错误占位图
     */
    private int imgError = -1;


    public UiConfig setDefColor(int defColor) {

        this.defColor = defColor;
        return this;
    }


    public UiConfig setLimitColor(int limitcolor) {

        this.limitcolor = limitcolor;
        return this;
    }

    public UiConfig setDefClickable(boolean defClickable) {

        this.defClickable = defClickable;
        return this;
    }

    public UiConfig setLimitNumber(int limitNumber) {

        this.limitNumber = limitNumber;
        return this;
    }

    public UiConfig setLimitcolor(int limitcolor) {
        this.limitcolor = limitcolor;
        return this;
    }

    public UiConfig setDefBg(int defBg) {
        this.defBg = defBg;
        return this;
    }

    public UiConfig setLimitBg(int limitg) {
        this.limitBg = limitg;
        return this;
    }


    // ---------------------------------



    public int getImgPlaceholder() {
        return imgPlaceholder;
    }

    public int getImgError() {
        return imgError;
    }

    public int getDefColor() {
        return defColor;
    }

    public int getLimitcolor() {
        return limitcolor;
    }

    public boolean isDefClickable() {
        return defClickable;
    }

    public int getLimitNumber() {
        return limitNumber;
    }


    public int getDefBg() {
        return defBg;
    }

    public int getLimitBg() {
        return limitBg;
    }
}
