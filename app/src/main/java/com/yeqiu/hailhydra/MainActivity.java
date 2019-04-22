package com.yeqiu.hailhydra;

import android.view.View;

import com.yeqiu.hydra.HydraUtilsManager;
import com.yeqiu.hydra.basecontroller.HydraBaseActivity;
import com.yeqiu.hydra.utils.UIHelper;

public class MainActivity extends HydraBaseActivity {


    @Override
    protected Object getContentView() {

        HydraUtilsManager.getInstance().init(getApplication());

        return R.layout.activity_test_1;
    }

    @Override
    protected void initView() {

        statusLayout.setEmptyView(R.layout.activity_test_2, R.id.tv_2);
        statusLayout.setErrorView(R.layout.activity_test_3, R.id.tv_3);
        statusLayout.setLoadingView(R.layout.activity_test_4, R.id.tv_4);


        statusLayout.showContentView();



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getDefHeadBackImgId() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStatusClick(View view) {

        switch (view.getId()) {

            case R.id.tv_2:
                UIHelper.showToast("空");
                break;

            case R.id.tv_3:
                UIHelper.showToast("错误");
                break;

            case R.id.tv_4:
                UIHelper.showToast("加载");
                break;

            case R.id.tv_1:
                UIHelper.showToast("内容");
                break;


            default:
                break;
        }
    }
}
