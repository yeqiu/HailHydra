package com.yeqiu.hydra.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.widget.StatusLayout.StatusLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-07-24
 * @describe：
 * @fix：
 */
public class StatusLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    StatusLayout statusLayout;
    TextView tvContent;
    TextView tvEmpty;
    TextView tvError;
    TextView tvLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuslayout);

        statusLayout = (StatusLayout) findViewById(R.id.status_layout);


        tvContent = (TextView) findViewById(R.id.tv_content);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
        tvError = (TextView) findViewById(R.id.tv_error);
        tvLoading = (TextView) findViewById(R.id.tv_loading);

        tvContent.setOnClickListener(this);
        tvEmpty.setOnClickListener(this);
        tvError.setOnClickListener(this);
        tvLoading.setOnClickListener(this);

        statusLayout.setContentView(R.layout.layout_content);
        statusLayout.setEmptyView(R.layout.layout_empty);
        statusLayout.setErrorView(R.layout.layout_error);
        statusLayout.setLoadingView(R.layout.layout_loading);

        statusLayout.showContentView();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content:
                statusLayout.showContentView();
                break;
            case R.id.tv_empty:
                statusLayout.showEmptyView();
                break;
            case R.id.tv_error:
                statusLayout.showErrorView();
                break;
            case R.id.tv_loading:
                statusLayout.showLoadingView();
                break;
            default:
                break;
        }
    }


}
