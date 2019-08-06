package com.yeqiu.hydra.ui.edittextutils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：点击清空输入框
 * @fix：
 */
public class EtClickCleanInput {


    /**
     * 点击清空输入框 当输入框没有内容的时候隐藏点击的view
     *
     * @param cleanView
     * @param editText
     */
    public void clickCleanInput(final View cleanView, final EditText editText) {

        cleanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    cleanView.setVisibility(View.GONE);
                } else {
                    cleanView.setVisibility(View.VISIBLE);
                }
            }
        });

    }


}
