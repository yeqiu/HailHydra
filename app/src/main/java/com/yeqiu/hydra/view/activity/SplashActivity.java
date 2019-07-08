package com.yeqiu.hydra.view.activity;

import android.os.Handler;
import android.view.View;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.ActivityUtils;


/**
 * @project：jinjuyunchuang
 * @author：小卷子
 * @date 2019/06/26
 * @describe：启动欢迎页
 * @fix：
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected Object getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        showHeadLayout(false);
    }

    @Override
    protected void initData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtils.toMainActivity();
                finish();
            }
        }, 2000);


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
