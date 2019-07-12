package com.yeqiu.hydra.view.activity;

import android.view.View;
import android.widget.TextView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.ResourceUtil;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/9
 * @describe：
 * @fix：
 */
public class BaseExplainActivity extends BaseActivity {

    private TextView tvBaseExplainActivity;
    private TextView tvBaseExplainFragment;
    private TextView tvBaseExplainDialog;
    private TextView tvBaseExplain;


    private String activity;
    private String fragment;
    private String dialog;

    @Override
    protected Object getContentView() {
        return R.layout.activity_base_explain;
    }

    @Override
    protected void initView() {

        setHeaderTitle("base的介绍");
        tvBaseExplainActivity = (TextView) findViewById(R.id.tv_base_explain_activity);
        tvBaseExplainFragment = (TextView) findViewById(R.id.tv_base_explain_fragment);
        tvBaseExplainDialog = (TextView) findViewById(R.id.tv_base_explain_dialog);
        tvBaseExplain = (TextView) findViewById(R.id.tv_base_explain);
    }

    @Override
    protected void initData() {


        activity = ResourceUtil.getString(R.string.activty_explain);
        fragment = ResourceUtil.getString(R.string.fragment_explain);
        dialog = ResourceUtil.getString(R.string.dialog_explain);

        tvBaseExplain.setText(activity);
    }

    @Override
    protected void initListener() {
        tvBaseExplainActivity.setOnClickListener(this);
        tvBaseExplainFragment.setOnClickListener(this);
        tvBaseExplainDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_explain_activity:
                tvBaseExplain.setText(activity);
                break;

            case R.id.tv_base_explain_fragment:
                tvBaseExplain.setText(fragment);
                break;

            case R.id.tv_base_explain_dialog:
                tvBaseExplain.setText(dialog);
                break;

            default:

                break;
        }
    }


}
