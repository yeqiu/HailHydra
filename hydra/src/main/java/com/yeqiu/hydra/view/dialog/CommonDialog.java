package com.yeqiu.hydra.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydrautils.R;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16
 * @describe：ios风格dialog
 * @fix：
 */
public class CommonDialog extends HydraBaseDialog<CommonDialog> implements View.OnClickListener {


    private boolean justConfirm = false;
    private String confirmText = "确定";
    private String descText = "";
    private int confirmColor = R.color.color_808080;
    private int descSize = 15;
    private int confirmSizer = 15;


    public CommonDialog(Activity context) {
        super(context);
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_common_dialog;
    }


    @Override
    protected void initDialog(View view) {

        TextView title = (TextView) view.findViewById(R.id.tv_common_dialog_title);
        TextView content = (TextView) view.findViewById(R.id.tv_common_dialog_content);
        View line = view.findViewById(R.id.v_common_dialog_line);
        TextView cancel = (TextView) view.findViewById(R.id.tv_common_dialog_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_common_dialog_confirm);
        //设置文字

        title.setText(getTitleText());
        content.setText(getDescText());
        cancel.setText(getCancelText());
        confirm.setText(getConfirmText());

        //设置颜色
        confirm.setTextColor(ResourceUtil.getColor(getConfirmColor()));
        cancel.setTextColor(ResourceUtil.getColor(getConfirmColor()));
        title.setTextColor(ResourceUtil.getColor(getConfirmColor()));
        content.setTextColor(ResourceUtil.getColor(getConfirmColor()));

        //设置文字大小
        title.setTextSize(getTitleSize());
        content.setTextSize(getDescSize());
        confirm.setTextSize(getConfirmSizer());
        cancel.setTextSize(getCancelSize());

        //设置取消键是否显示
        boolean justConfirm = isJustConfirm();
        cancel.setVisibility(justConfirm ? View.GONE : View.VISIBLE);
        line.setVisibility(justConfirm ? View.GONE : View.VISIBLE);

        //设置点击外面 返回是否可以隐藏
        dialog.setCancelable(isBackCancel());
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.tv_common_dialog_confirm) {
            if (getDialogListener() != null) {
                getDialogListener().onConfirmClick();
            }
            //关闭弹窗
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

        } else if (i == R.id.tv_common_dialog_cancel) {
            if (getDialogListener() != null) {
                getDialogListener().onCanceclClick();
            }
            //关闭弹窗
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

        }
    }


    //==========设置数据==========

    /**
     * 设置仅仅只有确认键
     */
    public CommonDialog setJustConfirm(boolean justConfirm) {
        this.justConfirm = justConfirm;
        return this;
    }

    /**
     * 确认键文字
     */
    public CommonDialog setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }



    /**
     * 内容文字
     */
    public CommonDialog setDescText(String descText) {
        this.descText = descText;
        return this;
    }

    /**
     * 确认的颜色
     */
    public CommonDialog setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }



    /**
     * 内容的文字大小
     */
    public CommonDialog setDescSize(int descSize) {
        this.descSize = descSize;
        return this;
    }

    /**
     * 确认的文字大小
     */
    public CommonDialog setConfirmSizer(int confirmSizer) {
        this.confirmSizer = confirmSizer;
        return this;
    }



    //==========get()==========


    protected boolean isJustConfirm() {
        return justConfirm;
    }

    protected String getConfirmText() {
        return confirmText == null ? "" : confirmText;
    }


    protected String getDescText() {
        return descText == null ? "" : descText;
    }

    protected int getConfirmColor() {
        return confirmColor;
    }


    protected int getDescSize() {
        return descSize;
    }

    protected int getConfirmSizer() {
        return confirmSizer;
    }




}
