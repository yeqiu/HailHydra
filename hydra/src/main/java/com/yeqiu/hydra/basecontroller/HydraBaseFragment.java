package com.yeqiu.hydra.basecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeqiu.hydra.utils.UIHelper;
import com.yeqiu.hydra.utils.net.NetWorkUtils;
import com.yeqiu.hydra.widget.StatusLayout.StatusLayout;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public abstract class HydraBaseFragment extends LifeCycleFragment {

    protected StatusLayout contentView;
    protected Activity context;


    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        contentView = new StatusLayout(getActivity());
        contentView.setContentView(getContentView());
        contentView.showContentView();
        context = getActivity();
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









}
