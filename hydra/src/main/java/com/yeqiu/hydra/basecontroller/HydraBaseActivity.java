package com.yeqiu.hydra.basecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yeqiu.hydra.net.OkGoManager;
import com.yeqiu.hydra.utils.ActivityManager;
import com.yeqiu.hydra.utils.KeybordUtils;
import com.yeqiu.hydra.utils.LogUtils;
import com.yeqiu.hydra.utils.ScreenUtils;
import com.yeqiu.hydra.utils.UIHelper;
import com.yeqiu.hydra.utils.net.NetWorkUtils;
import com.yeqiu.hydra.widget.StatusLayout.OnStatusClickListener;
import com.yeqiu.hydra.widget.StatusLayout.StatusLayout;
import com.yeqiu.hydrautils.R;

import org.greenrobot.eventbus.EventBus;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：activity
 */
public abstract class HydraBaseActivity extends SwipeBackActivity implements View
        .OnClickListener, OnStatusClickListener {

    protected StatusLayout statusLayout;
    protected LinearLayout headLayout;
    protected Activity context;
    protected ImageView ivHeadBack;
    protected TextView headerTitle;
    protected TextView tvheaderRight;
    protected ImageView ivheaderRight;
    protected View headLine;
    protected ImmersionBar imersionBar;
    protected RelativeLayout rlCommonHeadRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context = this;
        ///隐藏ActionBar
        isShowActionBar();
        init();
        //添加到activity管理器
        ActivityManager.getAppManager().addActivity(this);
        setSwipeBackEnable(isSwipeBack());
    }


    private void isShowActionBar() {

        if (removeActionBar()) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

        } else {
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        }

    }


    private void init() {
        statusLayout = (StatusLayout) findViewById(R.id.base_status_layout);
        statusLayout.setContentView(getContentView());
        statusLayout.showContentView();
        statusLayout.setOnStatusClickListener(this);
        initImmersionBar();
        registerEventBus();
        initHead();
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
        rlCommonHeadRoot = (RelativeLayout) findViewById(R.id.rl_common_head_root);
        headLine = findViewById(R.id.head_lien);
        //默认隐藏右侧的图片和文字
        tvheaderRight.setVisibility(View.GONE);
        ivheaderRight.setVisibility(View.GONE);
        ivHeadBack.setOnClickListener(onClickListener);
        tvheaderRight.setOnClickListener(onClickListener);
        ivheaderRight.setOnClickListener(onClickListener);
        ivHeadBack.setBackgroundResource(getDefHeadBackImgId());

        setHeadRootMarginTop();
    }

    /**
     * 设置标题栏距离顶部的距离，让布局定在状态栏下面
     * 当顶部是图片的时候建议重写此方法让图片顶在屏幕下面
     */
    private void setHeadRootMarginTop() {

        int statusHeight = ScreenUtils.getStatusHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlCommonHeadRoot
                .getLayoutParams();
        layoutParams.setMargins(0, statusHeight, 0, 0);
        rlCommonHeadRoot.setLayoutParams(layoutParams);

    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {

        if (isImmersionBarEnabled()) {
            imersionBar = ImmersionBar.with(this)
                    .keyboardEnable(true)
                    .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION);
            imersionBar.init();

            setStatusBarDarkFont(true);
        }
    }


    /**
     * 注册EventBus
     */
    private void registerEventBus(){

        if (isRegisterEventBus()){

            EventBus.getDefault().register(this);
        }

    }


    /**
     * 标题栏返回点击
     */
    protected void onBackClick() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);

        if (isImmersionBarEnabled()) {
            ImmersionBar.with(this).destroy();
        }

        if (isRegisterEventBus()){
            EventBus.getDefault().unregister(this);
        }

        //取消网络请求
        OkGoManager.getInstance().cancelRequest(this);
    }


    @Override
    public void finish() {
        //关闭本页的输入法
        KeybordUtils.closeKeybord(this);
        super.finish();
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
     * 初始化监听器
     */
    protected abstract void initListener();

    /**
     * 设置返回键图片
     */
    protected abstract int getDefHeadBackImgId();


    //    --------- 以下方法供子类使用  ---------


    /**
     * 删除ActionBar
     */
    protected boolean removeActionBar() {

        return true;
    }

    /**
     * 设置标题栏颜色
     *
     * @param colorId
     * @param backSrcId
     */
    protected void setHeadLayoutColor(int colorId, int backSrcId) {
        headLayout.setBackgroundColor(getResources().getColor(colorId));
        ivHeadBack.setImageResource(backSrcId);
    }

    /**
     * 状态栏字体颜色 亮色或深色，默认深色
     */
    protected void setStatusBarDarkFont(boolean isrDark) {

        if (isrDark) {
            if (ImmersionBar.isSupportStatusBarDarkFont()) {
                imersionBar.statusBarDarkFont(true).init();
            } else {
                LogUtils.i("当前设备不支持状态栏字体变色");
            }
        } else {
            imersionBar.statusBarDarkFont(false).init();
        }

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

    /**
     * 是否可以使用沉浸式
     *
     * @return
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 是否注册EventBus
     *
     * @return
     */
    protected boolean isRegisterEventBus(){

        return false;
    }



    /**
     * 是否使用侧滑返回
     *
     * @return
     */
    protected boolean isSwipeBack() {
        return true;
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


    /**
     * 隐藏键盘
     */
    protected void hideKeyBoard() {
        KeybordUtils.closeKeybord(context);
    }


    /**
     * 显示正常数据页面
     */
    protected void showContentView() {
        statusLayout.showContentView();

    }


    /**
     * 显示错误数据页面
     */
    protected void showErrorView() {
        statusLayout.showErrorView();

    }


    /**
     * 显示空数据页面
     */
    protected void showEmptyView() {
        statusLayout.showEmptyView();

    }


    /**
     * 显示加载数据页面
     */
    protected void showLoadingView() {
        statusLayout.showLoadingView();

    }

    /**
     * 显示自定义数据页面
     */
    protected void showCustomView() {
        statusLayout.showCustomView();

    }


    //    --------- 以下是空方法 子类选择实现  ---------


    /**
     * 状态布局的点击
     * @param view
     */
    @Override
    public void onStatusClick(View view) {

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


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.iv_common_head_back) {
                //左上角返回按钮统一处理
                onBackClick();

            } else if (i == R.id.tv_common_head_title_right) {
                onTvRightClick();

            } else if (i == R.id.iv_common_head_title_right) {
                onIvRightClick();

            }
        }
    };


}
