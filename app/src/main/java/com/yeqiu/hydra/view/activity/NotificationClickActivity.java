package com.yeqiu.hydra.view.activity;

import android.view.View;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.ToastUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-07-26
 * @describe：
 * @fix：
 */
public class NotificationClickActivity extends BaseActivity {
    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        setHeaderTitle("点击通知栏");


    }

    @Override
    protected void initData() {


        int data = getIntent().getIntExtra("data", 0);


        switch (data) {
            case 1:
                ToastUtils.showToast("暂停");
                break;
            case 2:
                ToastUtils.showToast("下一曲");
                break;
            case 3:
                ToastUtils.showToast("取消");
                break;


            default:
                break;
        }


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
