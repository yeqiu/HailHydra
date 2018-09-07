package com.yeqiu.hydrautils.ui.EditTextUtils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.yeqiu.hydrautils.common.ResourceUtil;
import com.yeqiu.hydrautils.ui.UiConfig;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：与EditText建立联系 满足条件更换view的背景
 * @fix：
 */
public class ConnectWithEditText {


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

    private View connectView;
    private EditText[] editTexts;


    public ConnectWithEditText setDefBg(int defBg) {
        this.defBg = defBg;
        return this;
    }

    public ConnectWithEditText setLimitg(int limitg) {
        this.limitg = limitg;
        return this;
    }

    public ConnectWithEditText setDefClickable(boolean defClickable) {
        this.defClickable = defClickable;
        return this;
    }


    public void connectWithEditText(View view, EditText... editTexts) {

        if (view == null || editTexts == null || editTexts.length <= 0) {
            return;
        }
        this.connectView = view;
        this.editTexts = editTexts;


        view.setClickable(defClickable);
        view.setBackground(ResourceUtil.getDrawable(defBg));

        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].addTextChangedListener(connectWatcher);
        }

    }


    /**
     * EditText与view关联
     */
    private TextWatcher connectWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isDone = true;

            for (EditText editText : editTexts) {
                String s1 = editText.getText().toString();
                if (TextUtils.isEmpty(s1)) {
                    isDone = false;
                    break;
                }
            }

            if (isDone) {
                connectView.setClickable(true);
                connectView.setBackground(ResourceUtil.getDrawable(limitg));
            } else {
                connectView.setClickable(defClickable);
                connectView.setBackground(ResourceUtil.getDrawable(defBg));
            }

        }
    };

}
