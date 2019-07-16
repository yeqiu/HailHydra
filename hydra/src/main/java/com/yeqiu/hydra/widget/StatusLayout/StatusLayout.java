package com.yeqiu.hydra.widget.StatusLayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/19
 * @describe：多状态的布局
 * @fix：
 */
public class StatusLayout extends FrameLayout implements View.OnClickListener {

    private Context context;

    private View emptyView;
    private View errorView;
    private View loadingView;
    private View contentView;
    private View customView;
    private View currentView;
    private OnStatusClickListener onStatusClickListener;

    public StatusLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        this.context = context;

    }


    /**
     * 设置空布局
     *
     * @param layoutIdOrView view 或者viewid
     * @param clickViewID    点击事件的id
     */
    public void setEmptyView(Object layoutIdOrView, @IdRes int... clickViewID) {

        if (layoutIdOrView instanceof View) {
            setEmptyView((View) layoutIdOrView, clickViewID);
        } else if (layoutIdOrView instanceof Integer) {
            int layoutId = (Integer) layoutIdOrView;
            setEmptyView(layoutId, clickViewID);
        } else {
            throw new IllegalArgumentException("layoutIdOrView must be View or ViewId");
        }
    }

    /**
     * 设置空布局
     *
     * @param resourceId  viewid
     * @param clickViewID 点击事件的id
     */
    private void setEmptyView(@LayoutRes int resourceId, @IdRes int... clickViewID) {

        if (emptyView == null) {
            emptyView = inflateView(resourceId);
        }
        setEmptyView(emptyView, clickViewID);

    }


    /**
     * 设置空布局
     *
     * @param view        view
     * @param clickViewID 点击事件的id
     */
    private void setEmptyView(View view, int... clickViewID) {

        if (emptyView == null) {
            emptyView = view;
        }

        addViewToStatusLayout(view);
        setClick(view, clickViewID);
    }

    /**
     * 显示空布局
     */
    public void showEmptyView() {

        if (emptyView == null) {
            throw new NullPointerException("You must first set up errorView");
        }
        showView(emptyView);
        currentView = emptyView;
    }


    /**
     * 设置错误布局
     *
     * @param layoutIdOrView view 或者viewid
     * @param clickViewID    点击事件的id
     */

    public void setErrorView(Object layoutIdOrView, @IdRes int... clickViewID) {

        if (layoutIdOrView instanceof View) {
            setErrorView((View) layoutIdOrView, clickViewID);
        } else if (layoutIdOrView instanceof Integer) {
            int layoutId = (Integer) layoutIdOrView;
            setErrorView(layoutId, clickViewID);
        } else {
            throw new IllegalArgumentException("layoutIdOrView must be View or ViewId");
        }
    }

    /**
     * 设置错误布局
     *
     * @param resourceId  viewid
     * @param clickViewID 点击事件的id
     */
    private void setErrorView(@LayoutRes int resourceId, @IdRes int... clickViewID) {

        if (errorView == null) {
            errorView = inflateView(resourceId);
        }
        setErrorView(errorView, clickViewID);

    }

    /**
     * 设置错误布局
     *
     * @param view        view
     * @param clickViewID 点击事件的id
     */
    private void setErrorView(View view, int... clickViewID) {

        addViewToStatusLayout(view);
        setClick(view, clickViewID);
    }


    /**
     * 显示错误布局
     */
    public void showErrorView() {

        if (errorView == null) {
            throw new NullPointerException("You must first set up errorView");
        }
        showView(errorView);
        currentView = errorView;
    }


    /**
     * 设置加载中布局
     *
     * @param layoutIdOrView view 或者viewid
     * @param clickViewID    点击事件的id
     */
    public void setLoadingView(Object layoutIdOrView, @IdRes int... clickViewID) {

        if (layoutIdOrView instanceof View) {
            setLoadingView((View) layoutIdOrView, clickViewID);
        } else if (layoutIdOrView instanceof Integer) {
            int layoutId = (Integer) layoutIdOrView;
            setLoadingView(layoutId, clickViewID);
        } else {
            throw new IllegalArgumentException("layoutIdOrView must be View or ViewId");
        }
    }

    /**
     * 设置加载中布局
     *
     * @param resourceId  viewid
     * @param clickViewID 点击事件的id
     */
    private void setLoadingView(@LayoutRes int resourceId, @IdRes int... clickViewID) {

        if (loadingView == null) {
            loadingView = inflateView(resourceId);
        }
        setLoadingView(loadingView, clickViewID);

    }

    /**
     * 设置加载中布局
     *
     * @param view        view
     * @param clickViewID 点击事件的id
     */

    private void setLoadingView(View view, int... clickViewID) {

        addViewToStatusLayout(view);
        setClick(view, clickViewID);
    }


    /**
     * 显示加载中
     */
    public void showLoadingView() {

        if (loadingView == null) {
            throw new NullPointerException("You must first set up loadingView");
        }
        showView(loadingView);
        currentView = loadingView;

    }

    /**
     * 设置内容布局
     *
     * @param layoutIdOrView view 或者viewid
     */
    public void setContentView(Object layoutIdOrView) {

        if (layoutIdOrView instanceof View) {
            setContentView((View) layoutIdOrView);
        } else if (layoutIdOrView instanceof Integer) {
            int layoutId = (Integer) layoutIdOrView;
            setContentView(layoutId);
        } else {
            throw new IllegalArgumentException("layoutIdOrView must be View or ViewId");
        }
    }

    /**
     * 设置内容布局
     *
     * @param resourceId  viewid
     */
    private void setContentView(@LayoutRes int resourceId) {

        if (contentView == null) {
            contentView = inflateView(resourceId);
        }
        setContentView(contentView);

    }

    /**
     * 设置内容布局
     *
     * @param view        view
     */
    private void setContentView(View view) {

        addViewToStatusLayout(view);
        setClick(view);
    }


    /**
     * 显示内容布局
     */
    public void showContentView() {

        if (contentView == null) {
            throw new NullPointerException("You must first set up contentView");
        }
        showView(contentView);
        currentView = contentView;

    }


    /**
     * 设置自定义布局
     *
     * @param layoutIdOrView view 或者viewid
     * @param clickViewID    点击事件的id
     */
    public void setCustomView(Object layoutIdOrView, @IdRes int... clickViewID) {

        if (layoutIdOrView instanceof View) {
            setCustomView((View) layoutIdOrView, clickViewID);
        } else if (layoutIdOrView instanceof Integer) {
            int layoutId = (Integer) layoutIdOrView;
            setCustomView(layoutId, clickViewID);
        } else {
            throw new IllegalArgumentException("layoutIdOrView must be View or ViewId");
        }
    }

    /**
     * 设置自定义布局
     *
     * @param resourceId  viewid
     * @param clickViewID 点击事件的id
     */
    private void setCustomView(@LayoutRes int resourceId, @IdRes int... clickViewID) {

        if (customView == null) {
            customView = inflateView(resourceId);
        }
        setCustomView(customView, clickViewID);

    }

    /**
     * 设置自定义布局
     *
     * @param view        view
     * @param clickViewID 点击事件的id
     */
    private void setCustomView(View view, int... clickViewID) {

        addViewToStatusLayout(view);
        setClick(view, clickViewID);
    }


    /**
     * 显示自定义布局
     */
    public void showCustomView() {

        if (customView == null) {
            throw new NullPointerException("You must first set up customView");
        }
        showView(customView);
        currentView = customView;

    }


    /**
     * 添加view到StatusLayout中
     *
     * @param view
     */
    private void addViewToStatusLayout(View view) {

        checkNull(view, "addViewToStatusLayout,view==null");

        if (containView(view)) {
            //已经包含此view
            return;
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        addView(view);

    }

    /**
     * 设置点击事件 并回调到onStatusClickListener
     *
     * @param view
     * @param clickViewID
     */
    private void setClick(View view, int... clickViewID) {

        for (int viewId : clickViewID) {
            View clickView = view.findViewById(viewId);
            if (clickView == null) {
                continue;
            }
            clickView.setOnClickListener(this);
        }
        //暂时隐藏view
        view.setVisibility(GONE);

    }


    /**
     * 是否包含当前view
     *
     * @param view
     */
    private boolean containView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("containView,view == null");
        }

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            if (view == getChildAt(i)) {
                return true;
            }
        }

        return false;

    }

    /**
     * null  check 传入null会抛出异常
     *
     * @param object
     * @param hint
     */
    private void checkNull(Object object, String hint) {
        if (null == object) {
            throw new NullPointerException(hint);
        }
    }


    /**
     * 显示指定的View
     *
     * @param view
     */
    private void showView(View view) {

        if (view == null) {
            throw new IllegalArgumentException("showView,view == null");
        }

        if (currentView == view) {
            //当前显示的就是指定的view
            return;
        }

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setVisibility(view == child ? View.VISIBLE : View.GONE);
        }

    }


    /**
     * 通过id构建view
     *
     * @param layoutId
     * @return
     */
    private View inflateView(int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }



    public void setOnStatusClickListener(OnStatusClickListener onStatusClickListener) {

        this.onStatusClickListener = onStatusClickListener;
    }

    @Override
    public void onClick(View v) {

        if (onStatusClickListener != null) {
            onStatusClickListener.onStatusClick(v);
        }
    }
}