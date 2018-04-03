package com.yeqiu.androiddome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;

import com.yeqiu.androiddome.Numberkeyboard;
import com.yeqiu.androiddome.R;

/**
 * @author ye
 * @date 2018/4/3
 * @desc
 */
public class NumberkeyActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberkey);
        et = (EditText) findViewById(R.id.et);
        //禁止弹出系统键盘
        et.setInputType(InputType.TYPE_NULL);
        Numberkeyboard keyboard = (Numberkeyboard) findViewById(R.id.keyboard);

        keyboard.setIOnKeyboardListener(new Numberkeyboard.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                et.append(text);
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
