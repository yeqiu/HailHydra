package com.yeqiu.hydra.basecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeqiu.hydra.utils.net.NetWorkUtils;
import com.yeqiu.hydra.widget.statuslayout.StatusLayout;

import androidx.annotation.Nullable;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public abstract class HydraBaseFragment extends LifeCycleFragment {

    private StatusLayout contentView;
    private Activity context;


    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        context = getActivity();
        contentView = new StatusLayout(context);
        contentView.setContentView(getContentView());
        contentView.showContentView();
        init();
        return contentView;
    }

    private void init() {
        initView();
        initData();
        initListener();
    }


    /**
     * 获取view
     *
     * @return
     */
    protected abstract Object getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    //    --------- 以下方法供子类使用  ---------

    /**
     * 查找控件
     *
     * @param id
     * @return
     */
    public View findViewById(int id) {
        return contentView.findViewById(id);

    }


    /**
     * 是否有网络
     */
    public boolean hasNet() {
        return NetWorkUtils.hasNetwork(context);
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
     * 显示正常数据页面
     */
    protected void showContentView() {
        contentView.showContentView();

    }


    /**
     * 显示错误数据页面
     */
    protected void showErrorView() {
        contentView.showErrorView();

    }


    /**
     * 显示空数据页面
     */
    protected void showEmptyView() {
        contentView.showEmptyView();

    }


    /**
     * 显示加载数据页面
     */
    protected void showLoadingView() {
        contentView.showLoadingView();

    }

    /**
     * 显示自定义数据页面
     */
    protected void showCustomView() {
        contentView.showCustomView();

    }

    @Nullable
    @Override
    public Activity getContext() {
        return context;
    }


    public View getRootView() {
        return contentView;
    }


}
