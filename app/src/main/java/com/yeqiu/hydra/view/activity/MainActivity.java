package com.yeqiu.hydra.view.activity;

import android.view.View;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.net.NetManager;
import com.yeqiu.hydra.net.bean.BaseBean;
import com.yeqiu.hydra.net.callback.dialogcallback.DialogCallback;
import com.yeqiu.hydra.utils.LogUtils;


public class MainActivity extends BaseActivity{


    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


        NetManager.getInstance().getAppUpgrade(context, new DialogCallback<String>() {
            @Override
            public void onNetSuccess(String data) {

                LogUtils.i("data = "+data);
            }
        });


        NetManager.getInstance().smsSend("111", "1", this, new DialogCallback<BaseBean>() {
            @Override
            public void onNetSuccess(BaseBean data) {

            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}