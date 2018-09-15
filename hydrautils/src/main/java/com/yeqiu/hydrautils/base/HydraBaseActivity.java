package com.yeqiu.hydrautils.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.common.ActivityManager;
import com.yeqiu.hydrautils.common.UIHelper;
import com.yeqiu.hydrautils.net.NetWorkUtils;
import com.yeqiu.hydrautils.ui.widget.StatusLayout;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public abstract class HydraBaseActivity extends AppCompatActivity {

    protected StatusLayout statusLayout;
    protected LinearLayout headLayout;
    protected Activity context;
    protected ImageView ivHeadBack;
    protected TextView headerTitle;
    protected TextView tvheaderRight;
    protected ImageView ivheaderRight;
    protected View headLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ///隐藏ActionBar
        removeActionBar(true);
        init();
        context = this;
        //添加到activity管理器
        ActivityManager.getAppManager().addActivity(this);
        statusLayout.setOnStatusLayoutClickListener(onStatusLayoutClickListener);
    }


    private void init() {
        statusLayout = (StatusLayout) findViewById(R.id.base_status_layout);
        statusLayout.setContentView(getContentView());
        statusLayout.showContentView();
        initHead();
        // TODO: 2018/9/15 沉浸式
        initView();
        initData();
        initListener();
    }


    /**
     * 初始化head标题栏
     * 标题栏中包括 返回 标题 右侧文字
     */
    private void initHead() {
        headLayout = (LinearLayout) findViewById(R.id.ll_common_header_layout);
        ivHeadBack = (ImageView) findViewById(R.id.iv_common_head_back);
        headerTitle = (TextView) findViewById(R.id.tv_common_head_title);
        tvheaderRight = (TextView) findViewById(R.id.tv_common_head_title_right);
        ivheaderRight = (ImageView) findViewById(R.id.iv_common_head_title_right);
        headLine = findViewById(R.id.head_lien);

        //默认隐藏右侧的图片和文字
        tvheaderRight.setVisibility(View.GONE);
        ivheaderRight.setVisibility(View.GONE);

        ivHeadBack.setOnClickListener(onClickListener);
        tvheaderRight.setOnClickListener(onClickListener);
        ivheaderRight.setOnClickListener(onClickListener);


        ivHeadBack.setBackgroundResource( getDefHeadBackImgId());
    }


    /**
     * 获取当前网络状态
     * 0:无网络
     * 1:网络断开或关闭
     * 2:以太网网络
     * 3:wifi网络
     * 4:移动数据连接
     */
    private int getNetStatus() {
        return NetWorkUtils.getNetworkType();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);

        // TODO: 2018/7/12 取消当前页面的网络请求
    }


    //    --------- 抽象方法  ---------


    /**
     * 获取view
     *
     * @return
     */
    protected abstract Object getContentView();


    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取数据
     */
    protected abstract void initData();

    /**
     * 设置返回键图片
     */
    protected abstract int getDefHeadBackImgId();


    //    --------- 以下方法供子类使用  ---------

    /**
     * 删除ActionBar
     *
     * @param isRemove
     */
    protected void removeActionBar(boolean isRemove) {
        if (isRemove) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }

    }


    /**
     * 设置head的标题
     * 自动显示标题栏
     */
    public void setHeaderTitle(String title) {
        headLayout.setVisibility(View.VISIBLE);
        headerTitle.setText(title);
    }

    /**
     * 设置左上角返回图片
     *
     * @param bakImg
     */
    public void setHeadBackImg(int bakImg) {

        ivHeadBack.setImageResource(bakImg);
    }

    /**
     * 显示标题栏右侧图片
     */
    public void showHeaderRightImageview(int imaId) {
        ivheaderRight.setVisibility(View.VISIBLE);
        ivheaderRight.setImageResource(imaId);
    }

    /**
     * 显示标题栏右侧文字
     */
    public void showHeaderRightTextview(String headTitle) {
        tvheaderRight.setVisibility(View.VISIBLE);
        tvheaderRight.setText(headTitle);

    }

    /**
     * 隐藏head底部的横线
     */
    public void hideHeadLine() {
        headLine.setVisibility(View.GONE);
    }

    /**
     * 显示正常数据页面
     */
    public void showContentView() {
        statusLayout.showContentView();

    }

    /**
     * 显示空数据页面
     *
     * @param picId           图片id
     * @param title           标题
     * @param emptyButtonText 按钮文字
     *                        传 "" 不显示该控件
     */
    public void showEmptyView(int picId, String title, String emptyButtonText) {
        statusLayout.showEmptyView(picId, title, emptyButtonText);
    }

    /**
     * 显示网络加载失败页面
     *
     * @param picId           图片id
     * @param title           标题
     * @param text            描述
     * @param errorButtonText 按钮文字
     *                        传 "" 不显示该控件
     */
    public void showErrorView(int picId, String title, String text, String errorButtonText) {
        statusLayout.showErrorView(picId, title, text, errorButtonText);

    }

    /**
     * 是否显示标题栏
     *
     * @param show
     */
    public void showHeadLayout(boolean show) {
        headLayout.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    /**
     * 设置窗口透明度
     *
     * @param f
     */
    public void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }


    /**
     * 是否有网络
     */
    public boolean hasNet() {
        return NetWorkUtils.hasNetwork(this);
    }


    /**
     * 显示网络提示
     */
    public void showNetTip() {
        int netStatus = getNetStatus();

        String netTip = "";
        switch (netStatus) {
            case 0:
                netTip = "无网络";
                break;

            case 1:
                netTip = "网络断开";
                break;
            case 2:
                netTip = "wifi";
                break;
            case 3:
                netTip = "wifi";
                break;
            case 4:
                netTip = "移动数据";
                break;

            default:
                break;

        }

        UIHelper.showToast(netTip);

    }


    //    --------- 以下是空方法 子类选择实现  ---------

    /**
     * 初始化监听器
     */
    protected void initListener() {
    }

    /**
     * 重新加载网页，子类实现
     */
    public void onStatusErrorButtonClick() {
    }

    /**
     * 空白页面的按钮，子类实现
     */
    public void onStatusEmptyButtonClick() {
    }


    /**
     * 标题右边的字点击事件
     */
    protected void onTvRightClick() {
    }

    /**
     * 标题右边的图标点击事件
     */
    protected void onIvRightClick() {
    }


    // --------- 监听  ---------

    private StatusLayout.OnStatusLayoutClickListener onStatusLayoutClickListener = new
            StatusLayout.OnStatusLayoutClickListener() {

                @Override
                public void onErrorButtonClick() {
                    onStatusErrorButtonClick();
                }

                @Override
                public void onEmptyButtonClick() {

                    onStatusEmptyButtonClick();
                }
            };


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.iv_common_head_back) {
                //左上角返回按钮统一处理
                finish();

            } else if (i == R.id.tv_common_head_title_right) {
                onTvRightClick();

            } else if (i == R.id.iv_common_head_title_right) {
                onIvRightClick();

            }
        }
    };
}
