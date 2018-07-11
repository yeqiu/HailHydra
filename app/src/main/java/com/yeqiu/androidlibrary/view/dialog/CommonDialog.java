package com.yeqiu.androidlibrary.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.androiddome.R;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16
 * @describe：ios风格dialog
 * @fix：
 */
public class CommonDialog extends BaseDialog implements View.OnClickListener {


    public CommonDialog(Activity context) {
        super(context);
    }

    @Override
    public Object getDiaologlayoutIdOrView() {
        return R.layout.layout_common_dialog;
    }


    @Override
    public void initViewEvent(View view) {

        TextView title = (TextView) view.findViewById(R.id.tv_common_dialog_title);
        TextView content = (TextView) view.findViewById(R.id.tv_common_dialog_content);
        View line = view.findViewById(R.id.v_common_dialog_line);
        TextView cancel = (TextView) view.findViewById(R.id.tv_common_dialog_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_common_dialog_confirm);
        //设置文字

        title.setText(dialogBuilder.getTitleText());
        content.setText(dialogBuilder.getDescText());
        cancel.setText(dialogBuilder.getCancelText());
        confirm.setText(dialogBuilder.getConfirmText());

        //设置颜色
        confirm.setTextColor(context.getResources().getColor(dialogBuilder.getConfirmColor()));
        cancel.setTextColor(context.getResources().getColor(dialogBuilder.getCancelColor()));
        title.setTextColor(context.getResources().getColor(dialogBuilder.getTitleColor()));
        content.setTextColor(context.getResources().getColor(dialogBuilder.getDescColor()));

        //设置文字大小
        title.setTextSize(dialogBuilder.getTitleSize());
        content.setTextSize(dialogBuilder.getDescSize());
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

        switch (v.getId()) {
            case R.id.tv_common_dialog_confirm:
                if (dialogBuilder.getDialogListener() != null) {
                    dialogBuilder.getDialogListener().onConfirmClick();
                }
                //关闭弹窗
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;

            case R.id.tv_common_dialog_cancel:
                if (dialogBuilder.getDialogListener() != null) {
                    dialogBuilder.getDialogListener().onCanceclClick();
                }
                //关闭弹窗
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
        }

    }
}
