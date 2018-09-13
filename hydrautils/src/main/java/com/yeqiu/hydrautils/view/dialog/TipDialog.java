package com.yeqiu.hydrautils.view.dialog;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.common.DensityUtils;
import com.yeqiu.hydrautils.view.dialog.base.BaseDialog;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/10
 * @describe：
 * @fix：
 */
public class TipDialog extends BaseDialog {


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

        LinearLayout llRoot = (LinearLayout) view.findViewById(R.id.ll_tip_dialog_root);


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
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams imageViewLP = new LinearLayout.LayoutParams(DensityUtils
                    .dp2px(28), DensityUtils.dp2px(28));
            imageView.setLayoutParams(imageViewLP);
            imageView.setImageResource(dialogBuilder.getIconId());
            llRoot.addView(imageView);
        }

        if (!TextUtils.isEmpty(dialogBuilder.getTipText())) {
            TextView tipView = new TextView(context);
            LinearLayout.LayoutParams tipViewLP = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //垂直布局
            tipViewLP.topMargin = DensityUtils.dp2px(12);
            tipViewLP.topMargin = dialogBuilder.getOrientationHorizontal() ? 0 : DensityUtils
                    .dp2px(12);

            //水平布局
            tipViewLP.leftMargin = dialogBuilder.getOrientationHorizontal() ? DensityUtils.dp2px
                    (12) : 0;

            tipView.setLayoutParams(tipViewLP);

            tipView.setEllipsize(TextUtils.TruncateAt.END);
            tipView.setGravity(Gravity.CENTER);
            tipView.setTextColor(ContextCompat.getColor(context, R.color.color_white));
            tipView.setTextSize(18);
            tipView.setText(dialogBuilder.getTipText());

            llRoot.addView(tipView);
        }

    }


}
