package com.yeqiu.hailhydra.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yeqiu.androiddome.R;


/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/19
 * @describe：设置与EditText绑定 只有EditText输入时view才能处于点击状态
 * @fix：
 */
public class EtUtils {

    private TextView codeView;

    /**
     * 当EditText输入满11位时view亮色显示
     *
     * @param view
     * @param phoneEt
     */
    public void sendCodeCheck(TextView view, EditText phoneEt) {

        if (view == null || phoneEt == null) {
            return;
        }
        this.codeView = view;
        view.setTextColor(view.getContext().getResources().getColor(R.color
                .color_b9b9b9));

        phoneEt.addTextChangedListener(phoneWatcher);

    }


    /**
     * 手机号满11位的监听
     */
    private TextWatcher phoneWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String phone = s.toString().trim();
            if (phone.length() >= 11) {
                codeView.setTextColor(codeView.getContext().getResources().getColor(R.color
                        .color_0d95ff));
            } else {
                codeView.setTextColor(codeView.getContext().getResources().getColor(R.color
                        .color_b9b9b9));
            }

        }
    };




    private View connectView;
    private EditText[] editTexts;

    /**
     * EditText与view关联，当所有EditText输入内容时才能点击
     *
     * @param view
     * @param editTexts
     */
    public void makeWithEtConnect(View view, EditText... editTexts) {

        if (view == null || editTexts == null || editTexts.length <= 0) {
            return;
        }

        this.connectView = view;
        this.editTexts = editTexts;


        view.setClickable(false);
        view.setBackground(view.getContext().getResources()
                .getDrawable(R.drawable.shape_40_0_0_e5e5e5));


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
                connectView.setBackground(connectView.getContext().getResources()
                        .getDrawable(R.drawable.shape_40_0_0_0d95ff));
            } else {
                connectView.setClickable(false);
                connectView.setBackground(connectView.getContext().getResources()
                        .getDrawable(R.drawable.shape_40_0_0_e5e5e5));
            }

        }
    };


    private TextView[] inputViews;
    private View inputBindView;

    /**
     * 检查所有输入框是否输入内容
     *
     * @param inputBindView
     * @param textViews
     * @return
     */
    public void checkInputDone(View inputBindView, TextView... textViews) {
        if (inputBindView == null || textViews == null || textViews.length <= 0) {
            return;
        }
        this.inputViews = textViews;
        this.inputBindView = inputBindView;

        inputBindView.setClickable(false);
        inputBindView.setBackground(inputBindView.getContext().getResources()
                .getDrawable(R.drawable.shape_40_0_0_e5e5e5));

        for (TextView textView : textViews) {
            textView.addTextChangedListener(InputTextWatcher);
        }


    }


    private TextWatcher InputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            boolean inputDone = true;

            for (TextView textView : inputViews) {
                String text = textView.getText().toString().trim();
                Log.i("test", "EtUtils:afterTextChanged, " + text);

                if (TextUtils.isEmpty(text)) {
                    inputDone = false;
                    break;
                }
            }

            if (inputDone) {
                inputBindView.setBackground(inputBindView.getContext().getResources()
                        .getDrawable(R.drawable.shape_40_0_0_0d95ff));
            } else {
                inputBindView.setClickable(false);
                inputBindView.setBackground(inputBindView.getContext().getResources()
                        .getDrawable(R.drawable.shape_40_0_0_0d95ff));
            }


        }
    };


}
