package com.yeqiu.androidlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yeqiu.androiddome.R;
import com.yeqiu.androidlibrary.utils.LogUtils;
import com.yeqiu.androidlibrary.utils.StatusBarUtils;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/12
 * @describe：
 * @fix：
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }


    public void baise(View view) {
        StatusBarUtils.setStatusBarTextWhite(this);
    }

    public void black(View view) {
        StatusBarUtils.setStatusBarTextBlack(this);


        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        LogUtils.i("statusBarHeight" + statusBarHeight);

    }
}
