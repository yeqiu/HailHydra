package com.yeqiu.hydrautils.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/7/31
 * @describe：
 * @fix：
 */
public abstract class LifeCycleFragment extends Fragment {

    /**
     * 上次是否不可见
     */
    private boolean isLastVisible = false;
    /**
     * 是否是第一次可见
     */
    private boolean isFirst = true;
    /**
     * 是否已经执行onResume
     */
    private boolean isResuming = false;
    /**
     * view是否已经创建
     */
    private boolean isViewCreate = false;
    /**
     * 是否被隐藏
     */
    private boolean hidden = false;
    private View contentView;

    protected abstract View getContentView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState);


    /**
     * fragment可见
     *
     * @param isFirst
     * @param isViewCreate
     */
    public  void onVisible(boolean isFirst, boolean isViewCreate){
        if (isViewCreate){
            onVisible();
        }

    }

    protected abstract void onVisible();


    /**
     * fragment不可见
     */
    protected  void onInvisible(){};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        contentView = getContentView(inflater, container, savedInstanceState);

        isViewCreate = true;
        isLastVisible = false;
        isFirst = true;
        hidden = false;

        return contentView;
    }


    @Override
    public void onResume() {
        super.onResume();
        isResuming = true;
        //  尝试设置可见
        trySetVisibility(true);
    }


    @Override
    public void onPause() {
        super.onPause();
        isResuming = false;
        //  尝试设置不可见
        trySetVisibility(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreate = false;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //设置子Fragment当前可见状态改变了
        setChildFragmentUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        setChildFragmentHiddenChanged(hidden);
    }


    /**
     * 尝试修改可见状态
     * 根据当前是否显示判断
     *
     * @param tryToShow
     */
    private void trySetVisibility(boolean tryToShow) {

        if (isLastVisible) {
            //当前可见
            if (tryToShow) {
                //尝试显示  当前已经是显示
                return;
            }

            //尝试隐藏
            if (!isFragmentVisible()) {
                //当前已经是不可见,回调隐藏方法
                onInvisible();
                isLastVisible = false;
            }

        } else {
            //当前不可见
            if (!tryToShow) {
                //尝试隐藏 当前已经是隐藏
                return;
            }

            if (isFragmentVisible()) {
                onVisible(isFirst, isViewCreate);
                isLastVisible = true;
                isFirst = false;
            }
        }

    }


    /**
     * Fragment是否可见
     *
     * @return
     */
    public boolean isFragmentVisible() {

        if (isResuming && getUserVisibleHint() && !hidden) {
            return true;
        }
        return false;
    }


    private void setChildFragmentUserVisibleHint(boolean isVisibleToUser) {
        // 尝试设置可见状态
        trySetVisibility(isVisibleToUser);
        if (isAdded()) {
            //已经被添加
            // 当Fragment状态改变，其子Fragment也状态改变。
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof LifeCycleFragment) {
                        ((LifeCycleFragment) fragment).setChildFragmentUserVisibleHint(isVisibleToUser);
                    }
                }
            }
        }
    }


    private void setChildFragmentHiddenChanged(boolean hidden) {

        this.hidden = hidden;
        trySetVisibility(!hidden);

        if (isAdded()) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof LifeCycleFragment) {
                        ((LifeCycleFragment) fragment).setChildFragmentHiddenChanged(hidden);
                    }
                }
            }
        }

    }


}
