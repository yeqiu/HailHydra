package com.yeqiu.android_tools.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yeqiu.android_tools.widget.NumberKeyBoardView;
import com.yeqiu.android_tools.widget.OnKeyboardListener;
import com.yeqiu.androiddome.R;

/**
 * @author ye
 * @date 2018/4/4
 * @desc 另一种自定义数字键盘
 */
public class NumberkeyActivity2 extends AppCompatActivity {

    private EditText et;
    private NumberKeyBoardView keyboardview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberkey2);
        et = (EditText) findViewById(R.id.et2);
        keyboardview = (NumberKeyBoardView) findViewById(R.id.number_key_board_view);


        //禁止弹出系统键盘
        et.setInputType(InputType.TYPE_NULL);


        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardview.openKeyboard();
            }
        });

        //显示小数点 默认不显示
        keyboardview.showPoit(true);

        keyboardview.setOnKeyboardListener(new OnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {

                if (TextUtils.equals(".", text)) {
                    if (!et.getText().toString().contains(".")) {
                        //只能输入一个小数点
                        et.append(text);
                    }
                } else {
                    et.append(text);
                }

            }

            @Override
            public void onDeleteKeyEvent() {
                int start = et.length() - 1;
                if (start >= 0) {
                    et.getText().delete(start, start + 1);
                }
            }
        });

    }
}
