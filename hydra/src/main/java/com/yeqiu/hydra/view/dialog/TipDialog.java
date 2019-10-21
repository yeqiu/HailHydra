package com.yeqiu.hydra.view.dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydra.widget.marquee.MarqueeTextView;
import com.yeqiu.hydrautils.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/10
 * @describe：
 * @fix：
 */
public class TipDialog extends HydraBaseDialog<TipDialog> {

    private ObjectAnimator animator;
    private ImageView imageView;
    private LinearLayout llRoot;
    private int iconId = -999;
    private String tipText;
    private boolean orientationHorizontal = true;
    private int dismissTime = 3000;
    private boolean isLoading = false;


    public TipDialog(Activity context) {
        super(context);
    }


    @Override
    protected int getstyle() {

        return R.style.TipDialog;
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {

        return R.layout.layout_tip_dialog;
    }


    @Override
    protected void initDialog(View view) {

        llRoot = (LinearLayout) view.findViewById(R.id.ll_tip_dialog_root);

        if (isOrientationHorizontal()) {
            llRoot.setOrientation(LinearLayout.HORIZONTAL);
            RelativeLayout.LayoutParams rootLayoutParams = (RelativeLayout.LayoutParams) llRoot
                    .getLayoutParams();
            rootLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            rootLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            llRoot.setLayoutParams(rootLayoutParams);

        } else {
            llRoot.setOrientation(LinearLayout.VERTICAL);
            RelativeLayout.LayoutParams rootLayoutParams = (RelativeLayout.LayoutParams) llRoot
                    .getLayoutParams();
            rootLayoutParams.width = DensityUtils.dp2px(100);
            rootLayoutParams.height = DensityUtils.dp2px(100);
            llRoot.setLayoutParams(rootLayoutParams);
        }


        if (getIconId() != -999) {
            imageView = new ImageView(getContext());
            LinearLayout.LayoutParams imageviewlp = new LinearLayout.LayoutParams(DensityUtils
                    .dp2px(30), DensityUtils.dp2px(30));
            imageView.setLayoutParams(imageviewlp);
            imageView.setImageResource(getIconId());
            llRoot.addView(imageView);
        }

        if (!TextUtils.isEmpty(getTipText())) {
            MarqueeTextView textView = new MarqueeTextView(getContext());
            LinearLayout.LayoutParams tipViewLP = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //垂直布局
            tipViewLP.topMargin = isOrientationHorizontal() ? 0 : DensityUtils.dp2px(10);

            //水平布局
            tipViewLP.leftMargin = isOrientationHorizontal() ? DensityUtils.dp2px(10) : 0;

            textView.setLayoutParams(tipViewLP);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSingleLine();
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.color_white));
            textView.setTextSize(14);
            textView.setText(getTipText());

            llRoot.addView(textView);
        }


        startAnimate();


    }


    public void startAnimate() {

        if (imageView != null && isLoading()) {
            animator = ObjectAnimator.ofFloat(imageView, "rotation",
                    0f, 360f);
            animator.setDuration(1000);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setRepeatMode(ObjectAnimator.RESTART);
            animator.start();
        }

    }



    @Override
    public TipDialog show() {

        int dismissTime = getDismissTime();
        if (dismissTime != 0 && llRoot != null) {
            llRoot.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissDialogWhitDelayer();
                }
            }, dismissTime);

        }
        return super.show();
    }

    @Override
    protected void onDialogDismiss() {
        super.onDialogDismiss();
        if (animator != null) {
            animator.end();
        }
    }


    //==========设置数据==========

    /**
     * 加载框的方向
     */
    public TipDialog setOrientationHorizontal(boolean orientationHorizontal) {
        this.orientationHorizontal = orientationHorizontal;
        return this;
    }

    /**
     * 加载框的消失的时间，默认30000，
     */
    public TipDialog setDismissTime(int dismissTime) {
        this.dismissTime = dismissTime;
        return this;
    }

    /**
     * 是否是加载框 加载框会执行旋转动画
     */
    public TipDialog setIsLoading(boolean loading) {
        isLoading = loading;
        return this;
    }

    /**
     * 图片id
     */
    public TipDialog setIconId(int iconId) {
        this.iconId = iconId;
        return this;
    }

    /**
     * tip的提示文字
     */
    public TipDialog setTipText(String tipText) {
        this.tipText = tipText;
        return this;
    }

    //==========get()==========


    private int getIconId() {
        return iconId;
    }

    private String getTipText() {
        return tipText == null ? "" : tipText;
    }

    private boolean isOrientationHorizontal() {
        return orientationHorizontal;
    }

    private int getDismissTime() {
        return dismissTime;
    }

    private boolean isLoading() {
        return isLoading;
    }
}
