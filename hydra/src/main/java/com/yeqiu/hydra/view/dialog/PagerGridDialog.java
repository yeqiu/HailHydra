package com.yeqiu.hydra.view.dialog;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydrautils.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/12
 * @describe：viewpager+grid 底部弹框（分享弹框）
 * @fix：
 */
public class PagerGridDialog extends HydraBaseDialog {


    private ViewPager vpPagerGrid;

    public PagerGridDialog(Activity context) {
        super(context);
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_pager_grid_dialog;
    }

    @Override
    protected void initDialog(View view) {

        vpPagerGrid = (ViewPager) findViewById(R.id.vp_pager_grid);





    }

/*
* 设置标题
* 设置指示器
*
*
* */



}
