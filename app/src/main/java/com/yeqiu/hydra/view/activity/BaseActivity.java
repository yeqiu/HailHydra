package com.yeqiu.hydra.view.activity;


import com.yeqiu.hydra.R;
import com.yeqiu.hydra.basecontroller.HydraBaseActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public abstract class BaseActivity extends HydraBaseActivity {


    @Override
    protected int getDefHeadBackImgId() {
        return R.drawable.head_back_gray;
    }


    @Override
    protected boolean isSwipeBack() {
        return true;
    }


}
