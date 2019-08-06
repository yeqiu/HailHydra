package com.yeqiu.hydra.ui.edittextutils;

import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.ui.UiConfig;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：检查所有的edittext是否输入完成 当所有输入框输入完成后 改变按钮背景变为可点击
 * @fix：
 */
public class EditTextInputDone {

    /**
     * 默认view的背景
     */
    private int defBg = UiConfig.getInstance().getDefBg();
    /**
     * 达到指定条件后view的背景
     */
    private int limitg = UiConfig.getInstance().getLimitBg();

    /**
     * 未达到指定条件view是否可以点击
     */
    private boolean defClickable = UiConfig.getInstance().isDefClickable();


    private TextView[] inputViews;
    private View inputBindView;

    public EditTextInputDone setDefBg(int defBg) {
        this.defBg = defBg;
        return this;
    }

    public EditTextInputDone setLimitg(int limitg) {
        this.limitg = limitg;
        return this;
    }

    public EditTextInputDone setDefClickable(boolean defClickable) {
        this.defClickable = defClickable;
        return this;
    }


    public void checkInputDone(View inputBindView, TextView... textViews) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }

        if (inputBindView == null || textViews == null || textViews.length <= 0) {
            return;
        }
        this.inputViews = textViews;
        this.inputBindView = inputBindView;

        inputBindView.setClickable(defClickable);
        inputBindView.setBackground(ResourceUtil.getDrawable(defBg));

        for (TextView textView : textViews) {
            textView.addTextChangedListener(inputTextWatcher);
        }
    }


    private TextWatcher inputTextWatcher = new TextWatcher() {
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

            boolean inputDone = true;

            for (TextView textView : inputViews) {
                String text = textView.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    inputDone = false;
                    break;
                }
            }

            if (inputDone) {
                inputBindView.setClickable(true);
                inputBindView.setBackground(ResourceUtil.getDrawable(limitg));
            } else {
                inputBindView.setClickable(defClickable);
                inputBindView.setBackground(ResourceUtil.getDrawable(defBg));
            }


        }
    };
}
