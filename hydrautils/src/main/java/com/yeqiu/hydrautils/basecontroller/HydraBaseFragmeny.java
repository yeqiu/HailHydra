package com.yeqiu.hydrautils.basecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeqiu.hydrautils.utils.UIHelper;
import com.yeqiu.hydrautils.utils.net.NetWorkUtils;
import com.yeqiu.hydrautils.widget.StatusLayout;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public abstract class HydraBaseFragmeny extends LifeCycleFragment {

    protected StatusLayout contentView;
    protected Activity context;


    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        contentView = new StatusLayout(getActivity());
        contentView.setContentView(getContentView());
        contentView.showContentView();
        contentView.setOnStatusLayoutClickListener(onStatusLayoutClickListener);
        context = getActivity();
        init();
        return contentView;
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    //    --------- 抽象方法  ---------

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    //    --------- 以下方法供子类使用  ---------

    /**
     * 显示正常数据页面
     */
    public void showContentView() {
        contentView.showContentView();

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
        contentView.showEmptyView(picId, title, emptyButtonText);
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
        contentView.showErrorView(picId, title, text, errorButtonText);

    }

    /**
     * 是否有网络
     */
    public boolean hasNet() {
        return NetWorkUtils.hasNetwork(context);
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


    //-------  以下是空方法 子类选择实现  ----------

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

}
