package com.yeqiu.hydra.view.dialog;

import android.app.Activity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yeqiu.hydra.utils.KeybordUtils;
import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydrautils.R;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/5/2
 * @describe：带输入框的dailog
 * @fix：
 */
public class EditDialog extends HydraBaseDialog<EditDialog> implements View.OnClickListener {


    private EditText edit;

    private boolean justConfirm = false;
    private String confirmText = "确定";
    private String hintText = "请输入";
    private int confirmColor = R.color.color_808080;
    private int descSize = 15;
    private int confirmSizer = 15;
    private int inputType = InputType.TYPE_CLASS_TEXT;
    private int inputSize = 15;


    public EditDialog(Activity context) {
        super(context);
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_edit_dialog;
    }

    @Override
    protected void initDialog(View view) {

        TextView title = (TextView) view.findViewById(R.id.tv_edit_dialog_title);
        edit = (EditText) view.findViewById(R.id.et_edit_dialog_edit);
        View line = view.findViewById(R.id.v_edit_dialog_line);
        TextView cancel = (TextView) view.findViewById(R.id.tv_edit_dialog_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_edit_dialog_confirm);

        //设置文字
        title.setText(getTitleText());
        edit.setHint(getHintText());
        cancel.setText(getCancelText());
        confirm.setText(getConfirmText());
        edit.setInputType(getInputType());

        //设置颜色
        confirm.setTextColor(ResourceUtil.getColor(getConfirmColor()));
        cancel.setTextColor(ResourceUtil.getColor(getCancelColor()));
        title.setTextColor(ResourceUtil.getColor(getTitleColor()));

        //设置文字大小
        title.setTextSize(getTitleSize());
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
        if (i == R.id.tv_edit_dialog_confirm) {
            if (getDialogListener() != null) {
                getDialogListener().onConfirmClick(edit.getText().toString());
            }
            //关闭弹窗
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

        } else if (i == R.id.tv_edit_dialog_cancel) {
            if (getDialogListener() != null) {
                getDialogListener().onCanceclClick(edit.getText().toString());
            }
            //关闭弹窗
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }


    @Override
    protected void onDialogDismiss() {
        super.onDialogDismiss();
        KeybordUtils.closeKeybord(getContext());
    }


    //==========设置数据==========

    /**
     * 设置仅仅只有确认键
     */
    public EditDialog setJustConfirm(boolean justConfirm) {
        this.justConfirm = justConfirm;
        return this;
    }

    /**
     * 确认键文字
     */
    public EditDialog setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }


    /**
     * 提示文字
     */
    public EditDialog setHint(String hintText) {
        this.hintText = hintText;
        return this;
    }

    /**
     * 确认的颜色
     */
    public EditDialog setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }


    /**
     * 内容的文字大小
     */
    public EditDialog setDescSize(int descSize) {
        this.descSize = descSize;
        return this;
    }

    /**
     * 确认的文字大小
     */
    public EditDialog setConfirmSizer(int confirmSizer) {
        this.confirmSizer = confirmSizer;
        return this;
    }


    /**
     * 输入框的输入类型
     */
    public EditDialog setInputType(int inputType) {
        this.inputType = inputType;
        return this;
    }

    /**
     * 输入框的文字大小
     */
    public EditDialog setInputSize(int inputSize) {
        this.inputSize = inputSize;
        return this;
    }

    //==========get()==========


    protected boolean isJustConfirm() {
        return justConfirm;
    }

    protected String getConfirmText() {
        return confirmText == null ? "" : confirmText;
    }

    protected String getHintText() {
        return hintText == null ? "" : hintText;
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

    public int getInputType() {
        return inputType;
    }

    public int getInputSize() {
        return inputSize;
    }
}
