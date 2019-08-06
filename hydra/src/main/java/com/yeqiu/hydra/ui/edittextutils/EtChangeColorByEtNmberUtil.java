package com.yeqiu.hydra.ui.edittextutils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.yeqiu.hydra.HydraUtilsManager;
import com.yeqiu.hydra.ui.UiConfig;
import com.yeqiu.hydra.utils.ResourceUtil;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：输入框达到指定位数 TextView 改变颜色 变为可点击
 * @fix：
 */
public class EtChangeColorByEtNmberUtil {


    /**
     * 默认view的颜色
     */
    private int defColor = UiConfig.getInstance().getDefColor();
    /**
     * 达到指定条件后view的颜色
     */
    private int limitcolor = UiConfig.getInstance().getLimitcolor();
    /**
     * 未达到指定条件view是否可以点击
     */
    private boolean defClickable = UiConfig.getInstance().isDefClickable();
    /**
     * 指定条件 字符串的数量
     */
    private int limitNumber = UiConfig.getInstance().getLimitNumber();


    private TextView codeView;


    public EtChangeColorByEtNmberUtil setDefColor(int defColor) {
        this.defColor = defColor;
        return this;
    }

    public EtChangeColorByEtNmberUtil setLimitcolor(int limitcolor) {
        this.limitcolor = limitcolor;
        return this;
    }

    public EtChangeColorByEtNmberUtil setDefClickable(boolean defClickable) {
        this.defClickable = defClickable;
        return this;
    }

    public EtChangeColorByEtNmberUtil setLimitNumber(int limitNumber) {
        this.limitNumber = limitNumber;
        return this;
    }


    public void changeColorByEtNmber(TextView view, EditText editText) {
        if (view == null || editText == null) {
            return;
        }
        this.codeView = view;


        view.setTextColor(ResourceUtil.getColor(defColor));
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
            String phone = s.toString().trim();
            if (phone.length() >= limitNumber) {
                codeView.setTextColor(HydraUtilsManager.getInstance().getContext().getResources()
                        .getColor(limitcolor));
                codeView.setClickable(true);
            } else {
                codeView.setTextColor(HydraUtilsManager.getInstance().getContext().getResources()
                        .getColor(defColor));
                codeView.setClickable(defClickable);
            }
        }
    };


}
