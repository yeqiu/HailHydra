package com.yeqiu.hailhydra.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yeqiu.hailhydra.widget.NumberKeyBoardView;
import com.yeqiu.hailhydra.widget.OnKeyboardListener;
import com.yeqiu.hailhydra.widget.PasswordView;
import com.yeqiu.androiddome.R;

/**
 * @author ye
 * @date 2018/4/8
 * @desc 密码输入框
 *
 * 这里只能使用自定义的键盘，使用系统键盘有在输入*和#的时候自定输入3 8 （暂时没有搞清楚什么原因）
 * 如果需要使用系统键盘参考
 * https://github.com/Ericsongyl/PasswordInputView
 * https://github.com/Jungerr/GridPasswordView
 *
 */
public class PasswordViewActivity extends AppCompatActivity{

    private NumberKeyBoardView keyBoard;
    private PasswordView pv_1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordview);
        pv_1 = (PasswordView) findViewById(R.id.pv_1);
        Button mode = (Button) findViewById(R.id.mode);
        keyBoard = (NumberKeyBoardView) findViewById(R.id.pv_1_key_board_view);

        //方框模式
        pv_1.setMode(PasswordView.Mode.RECT);
        pv_1.setPasswordLength(4);
        //不使用系统键盘
        pv_1.openKeyboard(false);
        //打开自定义键盘
        pv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开自定义键盘
                keyBoard.setVisibility(View.VISIBLE);

            }
        });
        //键盘监听
        keyBoard.setOnKeyboardListener(new OnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                pv_1.add(text);
            }

            @Override
            public void onDeleteKeyEvent() {
               pv_1.delete();
            }
        });


        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv_1.setMode(pv_1.getMode() == PasswordView.Mode.RECT ? PasswordView.Mode
                        .UNDERLINE : PasswordView.Mode.RECT);
            }
        });

    }
}
