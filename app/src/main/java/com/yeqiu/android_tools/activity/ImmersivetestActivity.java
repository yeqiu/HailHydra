package com.yeqiu.android_tools.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yeqiu.android_tools.utils.StatusBarUtil;
import com.yeqiu.androiddome.R;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

/**
 * @author ye
 * @date 2018/4/2
 * @desc 沉浸式状态栏
 * https://www.jianshu.com/p/29bf603359d6
 */
public class ImmersivetestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersivetest);


        //隐藏 ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }



        //6.0以支持 设置让应用的主体内容占用系统状态栏的空间
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
            //状态栏设置成透明的
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //底部导航栏设置成透明的
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }


        StatusBarUtil statusBarUtil = new StatusBarUtil();
        statusBarUtil.setStatusBarMode(this, true);




    }



}
