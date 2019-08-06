package com.yeqiu.hydra.ui.edittextutils;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.yeqiu.hydra.ui.UiConfig;
import com.yeqiu.hydra.utils.ResourceUtil;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/10/29
 * @describe：输入框达到指定位数 改变按钮颜色
 * @fix：
 */
public class EtChangeBackground {

    /**
     * 默认view的背景
     */
    protected int defBg = UiConfig.getInstance().getDefBg();
    /**
     * 达到指定条件后view的背景
     */
    protected int limitg = UiConfig.getInstance().getLimitBg();

    /**
     * 未达到指定条件view是否可以点击
     */
    protected boolean defClickable = UiConfig.getInstance().isDefClickable();

    /**
     * 指定条件 字符串的数量
     */
    protected int limitNumber = UiConfig.getInstance().getLimitNumber();


    private View codeView;


    public void changeBackgroundByEtNmber(View view, EditText editText) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }

        if (view == null || editText == null) {
            return;
        }
        this.codeView = view;

        view.setBackground(ResourceUtil.getDrawable(defBg));
        view.setClickable(defClickable);

        editText.addTextChangedListener(textWatcher);
    }


    /**
     * edittext输入监听
     */
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                return;
            }

            String phone = s.toString().trim();
            if (phone.length() >= limitNumber) {
                codeView.setBackground(ResourceUtil.getDrawable(limitg));
                codeView.setClickable(true);
            } else {
                codeView.setBackground(ResourceUtil.getDrawable(defBg));
                codeView.setClickable(defClickable);
            }
        }
    };
    //-------------- set --------------

    public int getDefBg() {
        return defBg;
    }

    public int getLimitg() {
        return limitg;
    }

    public boolean isDefClickable() {
        return defClickable;
    }

    public int getLimitNumber() {
        return limitNumber;
    }


    //-------------- get --------------

    public EtChangeBackground setDefBg(int defBg) {
        this.defBg = defBg;
        return this;
    }

    public EtChangeBackground setLimitg(int limitg) {
        this.limitg = limitg;
        return this;
    }

    public EtChangeBackground setDefClickable(boolean defClickable) {
        this.defClickable = defClickable;
        return this;
    }

    public EtChangeBackground setLimitNumber(int limitNumber) {
        this.limitNumber = limitNumber;
        return this;
    }


}
