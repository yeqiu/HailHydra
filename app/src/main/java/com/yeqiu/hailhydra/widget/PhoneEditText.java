package com.yeqiu.hailhydra.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @author ye
 * @date 2017/12/25
 * @desc 手机号输入框 自动添加 —
 */
public class PhoneEditText extends EditText{


    public PhoneEditText(Context context) {
        super(context);
        init();
    }

    public PhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initListener();
    }

    private void initListener() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == '-') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != '-') {
                            sb.insert(sb.length() - 1, '-');
                        }
                    }
                }

                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == '-') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    setText(sb.toString());
                    setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public String getPhone() {
        String phone = getText().toString().replaceAll("-","");
        return phone;
    }




}
