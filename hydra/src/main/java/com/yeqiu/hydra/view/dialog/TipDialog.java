package com.yeqiu.hydra.view.dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yeqiu.hydra.view.dialog.base.BaseDialog;
import com.yeqiu.hydrautils.R;
import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.widget.marquee.MarqueeTextView;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/10
 * @describe：
 * @fix：
 */
public class TipDialog extends BaseDialog {

    private ObjectAnimator animator;
    private ImageView imageView;
    private LinearLayout llRoot;

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
    protected void initView(View view) {

        llRoot = (LinearLayout) view.findViewById(R.id.ll_tip_dialog_root);


        if (dialogBuilder.getOrientationHorizontal()) {
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


        if (dialogBuilder.getIconId() != -999) {
            imageView = new ImageView(getContext());
            LinearLayout.LayoutParams imageViewLP = new LinearLayout.LayoutParams(DensityUtils
                    .dp2px(30), DensityUtils.dp2px(30));
            imageView.setLayoutParams(imageViewLP);
            imageView.setImageResource(dialogBuilder.getIconId());
            llRoot.addView(imageView);
        }


        if (!TextUtils.isEmpty(dialogBuilder.getTipText())) {
            MarqueeTextView textView = new MarqueeTextView(getContext());
            LinearLayout.LayoutParams tipViewLP = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //垂直布局
            tipViewLP.topMargin = dialogBuilder.getOrientationHorizontal() ? 0 : DensityUtils
                    .dp2px(10);

            //水平布局
            tipViewLP.leftMargin = dialogBuilder.getOrientationHorizontal() ? DensityUtils.dp2px
                    (10) : 0;

            textView.setLayoutParams(tipViewLP);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSingleLine();
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.color_white));
            textView.setTextSize(14);
            textView.setText(dialogBuilder.getTipText());

            llRoot.addView(textView);
        }


    }


    public void startAnimate() {

        if (imageView != null) {
            animator = ObjectAnimator.ofFloat(imageView, "rotation",
                    0f, 360f);
            animator.setDuration(1000);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setRepeatMode(ObjectAnimator.RESTART);
        }
        animator.start();


    }


    @Override
    public void show() {

        if (getContext() == null || getContext().isFinishing()) {
            return;
        }

        super.show();

        if (dialogBuilder.isLoading()) {
            startAnimate();
        }

        int dismissTime = dialogBuilder.getDismissTime();

        if (dismissTime != 0 && llRoot != null) {
            llRoot.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissDialog();
                }
            }, dismissTime);

        }


    }


    @Override
    protected void onDialogDismiss() {
        super.onDialogDismiss();
        if (animator != null) {
            animator.end();
        }
    }


}
