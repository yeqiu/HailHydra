package com.yeqiu.hydrautils.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.common.KeybordUtils;
import com.yeqiu.hydrautils.common.LogUtils;
import com.yeqiu.hydrautils.view.dialog.base.BaseDialog;

import static android.R.attr.inputType;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/5/2
 * @describe：带输入框的dailog
 * @fix：
 */
public class EditDialog extends BaseDialog implements View.OnClickListener {


    private EditText edit;

    public EditDialog(Activity context) {
        super(context);
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_edit_dialog;
    }

    @Override
    protected void initView(View view) {


        TextView title = (TextView) view.findViewById(R.id.tv_edit_dialog_title);
        edit = (EditText) view.findViewById(R.id.et_edit_dialog_edit);
        View line = view.findViewById(R.id.v_edit_dialog_line);
        TextView cancel = (TextView) view.findViewById(R.id.tv_edit_dialog_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_edit_dialog_confirm);

        //设置文字
        title.setText(dialogBuilder.getTitleText());
        edit.setHint(dialogBuilder.getHintText());
        cancel.setText(dialogBuilder.getCancelText());
        confirm.setText(dialogBuilder.getConfirmText());
        edit.setInputType(inputType);

        //设置颜色
        confirm.setTextColor(context.getResources().getColor(dialogBuilder.getConfirmColor()));
        cancel.setTextColor(context.getResources().getColor(dialogBuilder.getCancelColor()));
        title.setTextColor(context.getResources().getColor(dialogBuilder.getTitleColor()));

        //设置文字大小
        title.setTextSize(dialogBuilder.getTitleSize());
        confirm.setTextSize(dialogBuilder.getConfirmSizer());
        cancel.setTextSize(dialogBuilder.getCancelSize());

        //设置取消键是否显示
        boolean justConfirm = dialogBuilder.getJustConfirm();
        cancel.setVisibility(justConfirm ? View.GONE : View.VISIBLE);
        line.setVisibility(justConfirm ? View.GONE : View.VISIBLE);


        //设置点击外面 返回是否可以隐藏
        dialog.setCancelable(dialogBuilder.getIsBackCancel());
        dialog.setCanceledOnTouchOutside(dialogBuilder.getCanceledOnTouchOutside());

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.tv_edit_dialog_confirm) {
            if (dialogBuilder.getDialogListener() != null) {
                dialogBuilder.getDialogListener().onConfirmClick(edit.getText().toString());
            }
            //关闭弹窗
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

        } else if (i == R.id.tv_edit_dialog_cancel) {
            if (dialogBuilder.getDialogListener() != null) {
                dialogBuilder.getDialogListener().onCanceclClick(edit.getText().toString());
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

        LogUtils.i("onDialogDismiss");
        KeybordUtils.closeKeybord(context);

    }
}
