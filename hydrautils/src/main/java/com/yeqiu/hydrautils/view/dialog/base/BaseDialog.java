package com.yeqiu.hydrautils.view.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.utils.DensityUtils;
import com.yeqiu.hydrautils.view.dialog.DialogBuilder;

import java.lang.ref.WeakReference;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/11
 * @describe：
 * @fix：
 */
public abstract class BaseDialog {

    protected Dialog dialog;
    private WeakReference<Activity> context;
    protected DialogBuilder dialogBuilder;

    public BaseDialog(Activity context) {
        this.context = new WeakReference<>(context);
        dialog = new Dialog(context, getstyle());
        dialogBuilder = new DialogBuilder(this);

        setWindow();
    }


    /**
     * 获取 dialog的style
     * 默认 R.style.common_dialog
     *
     * @return
     */
    protected int getstyle() {
        return R.style.common_dialog;
    }

    /**
     * 设置dialog的Window属性
     * 默认距离两边30dp
     * 透明度0.98
     * 居中显示
     */
    protected void setWindow() {
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(DensityUtils.dp2px(30), 0, DensityUtils.dp2px(30), 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 0.98f;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
    }


    /**
     * 获取 dialogBuilder
     * 设置dialog的各种显示数据 标题 内容文字等
     *
     * @return
     */
    public DialogBuilder build() {

        return dialogBuilder;
    }


    /**
     * 当前的 dialog 是否正在显示
     *
     * @return
     */
    public boolean isShowing() {
        return dialog.isShowing();
    }


    /**
     * 显示 dialog
     */
    public void show() {

        Object layoutIdOrView = getDiaologlayoutIdOrView();

        if (layoutIdOrView == null) {
            throw new IllegalArgumentException("layoutIdOrView参数不能为null，可以是一个布局id，也可以是一个View对象");
        }

        View dialogView = null;

        if (layoutIdOrView instanceof View) {
            dialogView = (View) layoutIdOrView;
        } else {
            int layoutId = (Integer) layoutIdOrView;
            dialogView = inflateView(layoutId);
        }

        if (dialogView == null || dialog == null || dialogBuilder == null || getContext() == null ||
                getContext().isFinishing()) {
            //不符合现实弹窗的条件
            return;
        }

        initView(dialogView);
        dialog.setContentView(dialogView);
        dialog.show();

        clearOnDetach(dialog);


    }


    protected void onDialogDismiss() {
        if (dialogBuilder.getDialogListener() != null) {
            dialogBuilder.getDialogListener().onDialogDismiss();
        }
    }


    public void clearOnDetach(final Dialog dialog) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            dialog.getWindow()
                    .getDecorView()
                    .getViewTreeObserver()
                    .addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
                        @Override
                        public void onWindowAttached() {
                        }

                        @Override
                        public void onWindowDetached() {

                            onDialogDismiss();

                        }
                    });
        }

    }


    /**
     * 通过id构建view
     *
     * @param layoutId
     * @return
     */
    protected View inflateView(int layoutId) {

        if (context == null) {
            return null;
        }

        View view = LayoutInflater.from(getContext()).inflate(layoutId, null);

        return view;
    }


    /**
     * 获取dialog的view或者id
     *
     * @return
     */
    protected abstract Object getDiaologlayoutIdOrView();

    /**
     * 初始化view
     *
     * @param view
     */
    protected abstract void initView(View view);


    protected void dismissDialog() {

        if (dialog != null) {
            dialog.dismiss();
        }
    }


    protected Activity getContext() {

        return context.get();
    }

}
