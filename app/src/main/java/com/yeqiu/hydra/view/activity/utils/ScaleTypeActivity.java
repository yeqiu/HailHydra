package com.yeqiu.hydra.view.activity.utils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.view.activity.BaseActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/17
 * @describe：https://www.jianshu.com/p/32e335d5b842
 * @fix：
 */
public class ScaleTypeActivity extends BaseActivity {


    LinearLayout llScaletypeFitcenter;
    LinearLayout llScaletypeFitstart;
    LinearLayout llScaletypeFitend;
    LinearLayout llScaletypeFitxy;
    LinearLayout llScaletypeCenter;
    LinearLayout llScaletypeCentercrop;
    LinearLayout llScaletypeCenterinside;
    TextView tvScaletypeExplain;

    @Override
    protected Object getContentView() {
        return R.layout.activity_scaletype;
    }

    @Override
    protected void initView() {

        setHeaderTitle("ScaleType属性");

        llScaletypeFitcenter = (LinearLayout) findViewById(R.id.ll_scaletype_fitcenter);
        llScaletypeFitstart = (LinearLayout) findViewById(R.id.ll_scaletype_fitstart);
        llScaletypeFitend = (LinearLayout) findViewById(R.id.ll_scaletype_fitend);
        llScaletypeFitxy = (LinearLayout) findViewById(R.id.ll_scaletype_fitxy);
        llScaletypeCenter = (LinearLayout) findViewById(R.id.ll_scaletype_center);
        llScaletypeCentercrop = (LinearLayout) findViewById(R.id.ll_scaletype_centercrop);
        llScaletypeCenterinside = (LinearLayout) findViewById(R.id.ll_scaletype_centerinside);
        tvScaletypeExplain = (TextView) findViewById(R.id.tv_scaletype_explain);

    }

    @Override
    protected void initData() {

        tvScaletypeExplain.setText(R.string.fit_center_explain);

    }

    @Override
    protected void initListener() {

        llScaletypeFitcenter.setOnClickListener(this);
        llScaletypeFitstart.setOnClickListener(this);
        llScaletypeFitend.setOnClickListener(this);
        llScaletypeFitxy.setOnClickListener(this);
        llScaletypeCenter.setOnClickListener(this);
        llScaletypeCentercrop.setOnClickListener(this);
        llScaletypeCenterinside.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_scaletype_fitcenter:
                tvScaletypeExplain.setText(R.string.fit_center_explain);
                break;
            case R.id.ll_scaletype_fitstart:
                tvScaletypeExplain.setText(R.string.fit_start_explain);
                break;
            case R.id.ll_scaletype_fitend:
                tvScaletypeExplain.setText(R.string.fit_end_explain);
                break;
            case R.id.ll_scaletype_fitxy:
                tvScaletypeExplain.setText(R.string.fit_xy_explain);
                break;
            case R.id.ll_scaletype_center:
                tvScaletypeExplain.setText(R.string.center_explain);
                break;
            case R.id.ll_scaletype_centercrop:
                tvScaletypeExplain.setText(R.string.center_crop_explain);
                break;
            case R.id.ll_scaletype_centerinside:
                tvScaletypeExplain.setText(R.string.center_inside_explain);
                break;
            default:
                break;
        }
    }
}
