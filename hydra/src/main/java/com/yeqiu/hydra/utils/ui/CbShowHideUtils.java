package com.yeqiu.hydra.utils.ui;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/9
 * @describe：输入框明文密码切换
 * @fix：
 */
public class CbShowHideUtils {


    public void setShowHideListener(CheckBox cb, final EditText et) {


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                et.setTransformationMethod(isChecked ? HideReturnsTransformationMethod
                        .getInstance() : PasswordTransformationMethod.getInstance());
                et.setSelection(et.getText().toString().trim().length());
            }
        });

    }
}
