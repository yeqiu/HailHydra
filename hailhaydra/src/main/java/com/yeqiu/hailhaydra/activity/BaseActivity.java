package com.yeqiu.hailhaydra.activity;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydrautils.basecontroller.HydraBaseActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public abstract class BaseActivity extends HydraBaseActivity {


    @Override
    protected int getDefHeadBackImgId() {

        return R.drawable.icon_back;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        setStatusBarDarkFont(true);
    }





}
