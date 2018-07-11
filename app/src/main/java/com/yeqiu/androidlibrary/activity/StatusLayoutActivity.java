package com.yeqiu.androidlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yeqiu.androiddome.R;
import com.yeqiu.androidlibrary.widget.StatusLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/11
 * @describe：
 * @fix：
 */
public class StatusLayoutActivity extends AppCompatActivity {


    @BindView(R.id.sl_status)
    StatusLayout slStatus;
    @BindView(R.id.bt_status_loading)
    Button btStatusLoading;
    @BindView(R.id.bt_status_empty)
    Button btStatusEmpty;
    @BindView(R.id.bt_status_error)
    Button btStatusError;
    @BindView(R.id.bt_status_content)
    Button btStatusContent;
    @BindView(R.id.bt_status_custom)
    Button btStatusCustom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuslayout);
        ButterKnife.bind(this);

        slStatus.setContentView(R.layout.content);
        slStatus.showContentView();

    }

    @OnClick({R.id.bt_status_loading, R.id.bt_status_empty, R.id.bt_status_error, R.id
            .bt_status_content, R.id.bt_status_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_status_loading:
                slStatus.showLoadingView();
                break;
            case R.id.bt_status_empty:
                slStatus.showEmptyView(R.drawable.hydra, "空数据标题", "空数据按钮文字");
                break;
            case R.id.bt_status_error:
                slStatus.showErrorView(R.drawable.hydra, "错误标题", "错误描述", "错误按钮文字");
                break;
            case R.id.bt_status_content:
                slStatus.showContentView();
                break;
            case R.id.bt_status_custom:
                slStatus.showCustomView(R.layout.activity_dialog);
                break;
        }
    }
}
