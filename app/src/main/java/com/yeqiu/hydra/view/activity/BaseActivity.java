package com.yeqiu.hydra.view.activity;


import com.umeng.analytics.MobclickAgent;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.basecontroller.BaseHydraActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public abstract class BaseActivity extends BaseHydraActivity {



    @Override
    protected int getDefHeadBackImgId() {
        return R.drawable.head_back_gray;
    }


    @Override
    protected boolean isSwipeBack() {
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }



}
