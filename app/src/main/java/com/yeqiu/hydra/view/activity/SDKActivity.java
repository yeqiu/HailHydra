package com.yeqiu.hydra.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.view.activity.sdk.JPushActivity;
import com.yeqiu.hydra.view.activity.sdk.UmBuglyActivity;
import com.yeqiu.hydra.view.activity.sdk.WechatAliPayActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-09-18
 * @describe：
 * @fix：
 */
public class SDKActivity extends BaseActivity {

    private TextView tvSdkJg;
    private TextView tvSdkUmShare;
    private TextView tvSdkUmBugly;
    private TextView tvSdkPay;

    @Override
    protected Object getContentView() {
        return R.layout.activity_sdk;
    }

    @Override
    protected void initView() {

        setHeadTitle("集成常见三方sdk");
        tvSdkJg = (TextView) findViewById(R.id.tv_sdk_jg);
        tvSdkUmShare = (TextView) findViewById(R.id.tv_sdk_um_share);
        tvSdkUmBugly = (TextView) findViewById(R.id.tv_sdk_um_bugly);
        tvSdkPay = (TextView) findViewById(R.id.tv_sdk_pay);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {
        tvSdkJg.setOnClickListener(this);
        tvSdkUmShare.setOnClickListener(this);
        tvSdkUmBugly.setOnClickListener(this);
        tvSdkPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sdk_jg:
                toActivity(JPushActivity.class);
                break;

            case R.id.tv_sdk_um_share:

                break;
            case R.id.tv_sdk_um_bugly:
                toActivity(UmBuglyActivity.class);
                break;
            case R.id.tv_sdk_pay:

                toActivity(WechatAliPayActivity.class);
                break;
            default:
                break;
        }
    }

    private void toActivity(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }


}
