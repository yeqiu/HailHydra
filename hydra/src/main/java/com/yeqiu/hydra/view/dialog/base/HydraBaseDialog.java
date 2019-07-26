package com.yeqiu.hydra.view.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.view.dialog.callback.BaseDialogListener;
import com.yeqiu.hydrautils.R;

import java.lang.ref.WeakReference;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/11
 * @describe：
 * @fix：
 */
public abstract class HydraBaseDialog<T extends HydraBaseDialog> {

    protected Dialog dialog;
    private WeakReference<Activity> context;
    private View dialogView;
    private boolean canceledOnTouchOutside = true;
    private boolean isBackCancel = true;
    private String titleText = "";
    private BaseDialogListener dialogListener;
    private int titleSize = 15;
    private String cancelText = "取消";
    private int cancelSize = 15;
    private int cancelColor = R.color.color_808080;
    private int titleColor = R.color.color_808080;

    public HydraBaseDialog(Activity context) {
        this.context = new WeakReference<>(context);
        dialog = new Dialog(context, getstyle());
        setWindow();
    }


    public HydraBaseDialog getDialog() {

        return this;
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
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
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
    public T show() {

        Object layoutIdOrView = getDiaologlayoutIdOrView();

        if (layoutIdOrView == null) {
            throw new IllegalArgumentException("layoutIdOrView参数不能为null，可以是一个布局id，也可以是一个View对象");
        }

        if (layoutIdOrView instanceof View) {
            dialogView = (View) layoutIdOrView;
        } else {
            int layoutId = (Integer) layoutIdOrView;
            dialogView = inflateView(layoutId);
        }

        if (dialogView == null || dialog == null || getContext() == null ||
                getContext().isFinishing() || dialog.isShowing()) {
            //不符合现实弹窗的条件
            return (T) this;
        }

        initDialog(dialogView);
        dialog.setContentView(dialogView);
        //设置点击外面 返回是否可以隐藏
        dialog.setCancelable(isBackCancel());
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
        dialog.show();

        clearOnDetach(dialog);

        return (T) this;

    }


    protected void onDialogDismiss() {

        if (getDialogListener() != null) {
            getDialogListener().onDialogDismiss();
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
    protected abstract void initDialog(View view);


    /**
     * 延时200毫秒关闭
     */
    public void dismissDialogWhitDelayer() {

        if (dialog != null) {

            dialogView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 200);
        }
    }


    /**
     * 立即关闭
     */
    public void dismissDialogImmediately() {

        if (dialog != null) {

            dialog.dismiss();
        }
    }


    protected Activity getContext() {

        return context.get();
    }

    /**
     * 查找控件
     *
     * @param id
     * @return
     */
    public View findViewById(int id) {
        return dialogView.findViewById(id);

    }


    //==============设置数据==============

    /**
     * 设置标题文字大小
     */
    public T setTitleSize(int titleSize) {
        this.titleSize = titleSize;
        return (T) this;
    }

    /**
     * 点击外部是否消失
     */
    public T setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return (T) this;
    }

    /**
     * 点击返回是否消失
     */
    public T setBackCancel(boolean backCancel) {
        isBackCancel = backCancel;
        return (T) this;
    }

    /**
     * 标题文字
     */
    public T setTitleText(String titleText) {
        this.titleText = titleText;
        return (T) this;
    }

    /**
     * dialog监听
     */
    public T setOnDialogListener(BaseDialogListener dialogListener) {
        this.dialogListener = dialogListener;
        return (T) this;
    }

    /**
     * 取消键文字
     */
    public T setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return (T) this;
    }

    /**
     * 取消的颜色
     */
    public T setCancelColor(int cancelColor) {
        this.cancelColor = cancelColor;
        return (T) this;
    }

    /**
     * 标题的颜色
     */
    public T setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return (T) this;
    }

    /**
     * 取消的文字大小
     */
    public T setCancelSize(int cancelSize) {
        this.cancelSize = cancelSize;
        return (T) this;
    }

    //==============get()==============


    protected boolean isCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }

    protected boolean isBackCancel() {
        return isBackCancel;
    }

    protected String getTitleText() {
        return titleText == null ? "" : titleText;
    }

    protected BaseDialogListener getDialogListener() {
        return dialogListener;
    }

    protected int getTitleSize() {
        return titleSize;
    }

    public View getDialogView() {
        return dialogView;
    }

    protected String getCancelText() {
        return cancelText == null ? "" : cancelText;
    }

    protected int getCancelColor() {
        return cancelColor;
    }

    protected int getTitleColor() {
        return titleColor;
    }

    protected int getCancelSize() {
        return cancelSize;
    }

}
