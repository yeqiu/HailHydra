package com.yeqiu.hydra.basecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.yeqiu.hydra.net.OkGoManager;
import com.yeqiu.hydra.utils.ActivityManager;
import com.yeqiu.hydra.utils.KeybordUtils;
import com.yeqiu.hydra.utils.LogUtils;
import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.utils.ScreenUtils;
import com.yeqiu.hydra.utils.net.NetWorkUtils;
import com.yeqiu.hydra.widget.headbar.HydraHeadBar;
import com.yeqiu.hydra.widget.headbar.OnHeadBarClickListener;
import com.yeqiu.hydra.widget.statuslayout.OnStatusClickListener;
import com.yeqiu.hydra.widget.statuslayout.StatusLayout;
import com.yeqiu.hydrautils.R;

import org.greenrobot.eventbus.EventBus;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：activity
 */
public abstract class BaseHydraActivity extends SwipeBackActivity implements View
        .OnClickListener, OnStatusClickListener, OnHeadBarClickListener {

    protected LinearLayout llBaseRoot;
    protected StatusLayout statusLayout;
    protected ImmersionBar imersionBar;
    protected HydraHeadBar barLayout;
    protected int openEnterAnimation = R.anim.slide_right_to_left_in;
    protected int openExitAnimation = R.anim.slide_right_to_left_out;
    protected int closeEnterAnimation = R.anim.slide_left_to_right_in;
    protected int closeExitAnimation = R.anim.slide_left_to_right_out;
    private Activity context;
    private View statusBarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setActivityAnimation(openEnterAnimation, openExitAnimation, closeEnterAnimation,
                closeExitAnimation);
        showActivityAnimation(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context = this;
        //添加到activity管理器
        ActivityManager.getAppManager().addActivity(this);
        ///隐藏ActionBar
        isShowActionBar();
        setSwipeBackEnable(isSwipeBack());
        init();

    }

    /**
     * activty跳转动画
     *
     * @param isCreate
     */
    private void showActivityAnimation(boolean isCreate) {

        if (!isShowActivityAnimation()) {
            return;
        }

        if (isCreate) {
            overridePendingTransition(openEnterAnimation, openExitAnimation);
        } else {
            overridePendingTransition(closeEnterAnimation, closeExitAnimation);
        }

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
        llBaseRoot = (LinearLayout) findViewById(R.id.ll_base_root);
        statusLayout = (StatusLayout) findViewById(R.id.base_status_layout);
        statusLayout.setBackgroundColor(ResourceUtil.getColor(R.color.color_white));
        statusLayout.setContentView(getContentView());
        statusLayout.showContentView();
        statusLayout.setOnStatusClickListener(this);
        initImmersionBar();
        registerEventBus();
        initHead();
        initStatusLayout();
        initView();
        initData();
        initListener();
    }


    /**
     * 初始化head标题栏
     */
    private void initHead() {

        barLayout = (HydraHeadBar) findViewById(R.id.bar_layout);
        barLayout.setOnHydraHeadBarClickListener(this);

        //默认标题栏白色
        setHeadBarBackgroundColor(R.color.color_white);
    }


    /**
     * 添加状态栏占位视图
     * 如果页面顶部是图片，可以重新此方法 不添加任何占位
     *
     * @param colorId
     */
    protected void addStatusViewWithColor(int colorId) {


        if (!needStatusPlaceholderView()) {
            return;
        }

        if (statusBarView != null) {
            statusBarView.setBackgroundResource(colorId);
            return;
        }

        statusBarView = new View(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ScreenUtils.getStatusHeight());
        statusBarView.setBackgroundColor(ResourceUtil.getColor(colorId));
        llBaseRoot.addView(statusBarView, 0, lp);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setHeadTitle(String title) {

        barLayout.setHeadTitle(title);
    }


    /**
     * 设置返回图标
     */
    protected void setHeadBackImg() {

        barLayout.setHeadBackImg(getDefHeadBackImgId());
    }

    /**
     * 设置标题栏颜色
     *
     * @param color
     */
    protected void setHeadBarBackgroundColor(@ColorRes int color) {

        barLayout.setHeadBackgroundColor(color);
        addStatusViewWithColor(color);
    }

    /**
     * 是否显示顶部导航栏
     *
     * @param isShow
     */
    protected void showHeadBar(boolean isShow) {

        barLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
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

            setStatusBarDarkFont(isStatusBarDarkFont());
        }
    }


    /**
     * 注册EventBus
     */
    private void registerEventBus() {

        if (isRegisterEventBus()) {

            EventBus.getDefault().register(this);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);

        if (isImmersionBarEnabled()) {
            ImmersionBar.with(this).destroy();
        }

        if (isRegisterEventBus()) {
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
        showActivityAnimation(false);
    }


    //    --------- 抽象方法  ---------


    /**
     * 初始化多布局
     */
    protected abstract void initStatusLayout();


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
     *
     * @return
     */
    protected abstract int getDefHeadBackImgId();


    //    --------- 以下方法供子类使用  ---------

    /**
     * 设置activity跳转动画
     *
     * @param openEnterAnimation  A1启动A2，A2出现在屏幕上的动画
     * @param openExitAnimation   A1启动A2，A1从屏幕上消失的动画
     * @param closeEnterAnimation A2退回A1 A1出现在屏幕上的动画
     * @param closeExitAnimation  A2退回A1 A2从屏幕上消失的动画
     */
    protected void setActivityAnimation(int openEnterAnimation, int openExitAnimation,
                                        int closeEnterAnimation, int closeExitAnimation) {
        this.openEnterAnimation = openEnterAnimation;
        this.openExitAnimation = openExitAnimation;
        this.closeEnterAnimation = closeEnterAnimation;
        this.closeExitAnimation = closeExitAnimation;
    }


    /**
     * 删除ActionBar
     */
    protected boolean removeActionBar() {

        return true;
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
    protected int getNetStatus() {
        return NetWorkUtils.getNetworkType();
    }


    /**
     * 是否设置activity跳转动画
     *
     * @return
     */
    protected boolean isShowActivityAnimation() {
        return true;
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
     * 状态栏是否设置深色
     *
     * @return
     */
    protected boolean isStatusBarDarkFont() {
        return true;
    }


    /**
     * 是否注册EventBus
     *
     * @return
     */
    protected boolean isRegisterEventBus() {

        return false;
    }


    /**
     * 状态栏占位的颜色
     *
     * @return
     */
    protected int getStatusColorId() {
        return R.color.color_white;
    }

    /**
     * 返回本身实例
     *
     * @return
     */
    protected Activity getActivity() {
        return context;
    }

    /**
     * 是否使用侧滑返回
     *
     * @return
     */
    protected boolean isSwipeBack() {
        return false;
    }

    /**
     * 是否需要通知栏占位
     *
     * @return
     */
    protected boolean needStatusPlaceholderView() {

        return true;
    }

    /**
     * 设置窗口透明度
     *
     * @param f
     */
    protected void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }


    /**
     * 是否有网络
     */
    protected boolean hasNet() {
        return NetWorkUtils.hasNetwork(this);
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
     *
     * @param view
     */
    @Override
    public void onStatusClick(View view) {

    }


}
