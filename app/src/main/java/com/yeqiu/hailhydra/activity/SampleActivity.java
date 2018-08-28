package com.yeqiu.hailhydra.activity;

import android.view.View;
import android.widget.Button;

import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.utils.UIHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/12
 * @describe：
 * @fix：
 */
public class SampleActivity extends BaseActivity {
    @BindView(R.id.bt_sample_show_head)
    Button btSampleShowHead;
    @BindView(R.id.bt_sample_set_back_img)
    Button btSampleSetBackImg;
    @BindView(R.id.bt_sample_set_right_img)
    Button btSampleSetRightImg;
    @BindView(R.id.bt_sample_show_contentView)
    Button btSampleShowContentView;
    @BindView(R.id.bt_sample_show_errorView)
    Button btSampleShowErrorView;
    @BindView(R.id.bt_sample_has_net)
    Button btSampleHasNet;
    @BindView(R.id.bt_sample_net)
    Button btSampleNet;


    private boolean isShowHead = true;

    @Override
    protected int getContentView() {
        return R.layout.activity_sample;
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onErrorButtonClick() {
        super.onErrorButtonClick();
        showContentView();
    }


    @OnClick({R.id.bt_sample_show_head, R.id.bt_sample_set_back_img, R.id
            .bt_sample_set_right_img, R.id.bt_sample_show_contentView, R.id
            .bt_sample_show_errorView, R.id.bt_sample_has_net, R.id.bt_sample_net})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sample_show_head:

                showHeadLayout(!isShowHead);
                isShowHead = !isShowHead;
                break;
            case R.id.bt_sample_set_back_img:

                setHeadBackImg(R.mipmap.ic_launcher);
                break;
            case R.id.bt_sample_set_right_img:

                showHeaderRightImageview(R.mipmap.ic_launcher);
                break;
            case R.id.bt_sample_show_contentView:
                showContentView();

                break;
            case R.id.bt_sample_show_errorView:
                showErrorView(R.drawable.hydra, "SampleActivity标题", "SampleActivity描述",
                        "SampleActivity按钮文字");
                break;
            case R.id.bt_sample_has_net:

                UIHelper.showToast(context, hasNet() + "");
                break;
            case R.id.bt_sample_net:

                showNetTip();
                break;
        }
    }
}
