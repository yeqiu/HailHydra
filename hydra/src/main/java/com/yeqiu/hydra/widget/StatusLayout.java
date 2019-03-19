package com.yeqiu.hydra.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeqiu.hydrautils.R;


/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/10
 * @describe：多状态的布局
 * @fix：
 */
public class StatusLayout extends FrameLayout implements View.OnClickListener {


    private View emptyView;
    private View errorView;
    private View loadingView;
    private View contentView;
    private View customView;


    private LayoutInflater layoutInflater;
    private int currentViewId; //当前显示的view
    public static final int contentViewId = 0; //内容视图
    public static final int loadingViewId = 1; //加载视图
    public static final int emptyViewId = 2;   //空视图
    public static final int errorViewId = 3;   //加载错误视图
    public static final int customViewId = 4;   //自定义视图
    private ImageView emptyPic;
    private TextView emptyTitle;
    private TextView emptyButton;
    private ImageView errorImg;
    private TextView errorTitle;
    private TextView errorText;
    private TextView errorButton;
    private OnStatusLayoutClickListener onStatusLayoutClickListener;


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

        layoutInflater = LayoutInflater.from(context);
        emptyView = inflateView(R.layout.def_empty_view);
        errorView = inflateView(R.layout.def_error_view);
        loadingView = inflateView(R.layout.def_loading_view);


        emptyPic = (ImageView) emptyView.findViewById(R.id.iv_empty_img);
        emptyTitle = (TextView) emptyView.findViewById(R.id.tv_empty_title);
        emptyButton = (TextView) emptyView.findViewById(R.id.tv_empty_button);

        errorImg = (ImageView) errorView.findViewById(R.id.iv_error_img);
        errorTitle = (TextView) errorView.findViewById(R.id.tv_error_title);
        errorText = (TextView) errorView.findViewById(R.id.tv_error_text);
        errorButton = (TextView) errorView.findViewById(R.id.tv_error_reload);


        addViewToStatusLayout(emptyView);
        addViewToStatusLayout(errorView);
        addViewToStatusLayout(loadingView);


        emptyButton.setOnClickListener(this);
        errorButton.setOnClickListener(this);

        emptyView.setVisibility(GONE);
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // TODO: 2018/7/10 设置默认加载内容


    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //  view销毁删除所有子view
        cleanAllView(emptyView, loadingView, errorView);

        if (onStatusLayoutClickListener != null) {
            onStatusLayoutClickListener = null;
        }
    }


    /**
     * 设置一个正常界面的View，可以是一个布局id，也可以是一个View对象
     *
     * @param layoutIdOrView
     */
    public void setContentView(Object layoutIdOrView) {


        if (contentView != null) {
            throw new IllegalArgumentException("已经设置过ContentView,请勿重复添加");
        }

        if (layoutIdOrView == null) {
            throw new IllegalArgumentException("layoutIdOrView参数不能为null，可以是一个布局id，也可以是一个View对象");
        }

        if (layoutIdOrView instanceof View) {
            contentView = (View) layoutIdOrView;
        } else {
            int layoutId = (Integer) layoutIdOrView;
            contentView = inflateView(layoutId);
        }

        addViewToStatusLayout(contentView);

        contentView.setVisibility(View.GONE);
    }


    /**
     * 显示正常视图
     */
    public void showContentView() {

        showView(contentView);

        currentViewId = contentViewId;

    }


    /**
     * 显示空数据视图
     *
     * @param picId           图片id
     * @param title           标题
     * @param emptyButtonDesc 文字
     *                        没有参数传 ""
     */
    public void showEmptyView(int picId, String title, String emptyButtonDesc) {

        emptyPic.setImageResource(picId);
        emptyTitle.setText(title);
        emptyButton.setText(emptyButtonDesc);

        emptyTitle.setVisibility(TextUtils.isEmpty(title) ? GONE : VISIBLE);
        emptyButton.setVisibility(TextUtils.isEmpty(emptyButtonDesc) ? GONE : VISIBLE);

        showView(emptyView);

        currentViewId = emptyViewId;

    }


    /**
     * 显示加载中视图
     */
    public void showLoadingView() {

        showView(loadingView);
        currentViewId = loadingViewId;
    }


    /**
     * 添加自定义视图
     *
     * @param layoutIdOrView
     */
    public void showCustomView(Object layoutIdOrView) {

        if (layoutIdOrView == null) {
            throw new IllegalArgumentException("layoutIdOrView参数不能为null，可以是一个布局id，也可以是一个View对象");
        }


        if (customView !=null &&containView(customView)){
            //之前已经设置过customView
            removeView(customView);
        }


        if (layoutIdOrView instanceof View) {
            customView = (View) layoutIdOrView;
        } else {
            int layoutId = (Integer) layoutIdOrView;
            customView = inflateView(layoutId);
        }

        addViewToStatusLayout(customView);

        showCustomView();

    }


    /**
     * 显示自定义视图
     */
    public void showCustomView() {
        checkNull(customView, "未设置customView");

        if (containView(customView)) {
            showView(customView);
            currentViewId = customViewId;
        }
       


    }


    /**
     * 显示失败布局
     *
     * @param picId      图片id
     * @param title      标题
     * @param desc       描述
     * @param buttonDesc 文字
     *                   没有参数传 ""
     */
    public void showErrorView(int picId, String title, String desc, String buttonDesc) {

        errorImg.setImageResource(picId);

        errorTitle.setText(title);
        errorText.setText(desc);
        errorButton .setText(buttonDesc);

        errorTitle.setVisibility(TextUtils.isEmpty(title) ? GONE : VISIBLE);
        errorText.setVisibility(TextUtils.isEmpty(desc) ? GONE : VISIBLE);
        errorButton.setVisibility(TextUtils.isEmpty(buttonDesc) ? GONE : VISIBLE);

        showView(errorView);
        currentViewId = errorViewId;


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
     * 显示指定的View
     *
     * @param view
     */
    private void showView(View view) {

        if (view == null) {
            throw new IllegalArgumentException("showView,view == null");
        }

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            boolean b = child == view;

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
        return layoutInflater.inflate(layoutId, null);
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
     * 获取当前状态
     */
    public int getViewStatus() {
        return currentViewId;
    }


    private void addViewToStatusLayout(View view) {

        checkNull(view, "addViewToStatusLayout,view==null");

        if (!containView(view)) {
            addView(view);
        }
    }

    /**
     * 清除所有view
     *
     * @param views
     */
    private void cleanAllView(View... views) {
        if (views == null) {
            return;
        }
        try {
            for (View view : views) {
                if (view != null) {
                    removeView(view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        if (onStatusLayoutClickListener == null) {
            return;
        }

        int i = v.getId();
        if (i == R.id.tv_empty_button) {
            onStatusLayoutClickListener.onEmptyButtonClick();

        } else if (i == R.id.tv_error_reload) {
            onStatusLayoutClickListener.onErrorButtonClick();

        }

    }

    public void setOnStatusLayoutClickListener(OnStatusLayoutClickListener
                                                       onStatusLayoutClickListener) {

        this.onStatusLayoutClickListener = onStatusLayoutClickListener;
    }


    public interface OnStatusLayoutClickListener {

        void onErrorButtonClick();

        void onEmptyButtonClick();

    }
}


