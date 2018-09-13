package com.yeqiu.hydrautils.view.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.common.DensityUtils;
import com.yeqiu.hydrautils.view.dialog.base.BaseDialog;

import java.util.List;

;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/17
 * @describe：仿ios底部弹出dialog
 * @fix：
 */
public class SheetDialog extends BaseDialog {


    public SheetDialog(Activity context) {
        super(context);
    }

    @Override
    protected void setWindow() {
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(DensityUtils.dp2px( 10), 0, DensityUtils.dp2px
                (10), DensityUtils.dp2px(10));
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 0.98f;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);

    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_sheet_dialog;
    }

    @Override
    protected void initView(View view) {

        TextView title = (TextView) view.findViewById(R.id.tv_sheet_dialog_title);
        LinearLayout list = (LinearLayout) view.findViewById(R.id.ll_sheet_dialog_list);
        TextView cancel = (TextView) view.findViewById(R.id.tv_sheet_dialog_cancel);

        List<String> datas = dialogBuilder.getSheetDatas();

        if (datas == null || datas.size() == 0) {
            return;
        }

        if (TextUtils.isEmpty(dialogBuilder.getTitleText())) {
            //不显示标题
            title.setVisibility(View.GONE);
        } else {
            title.setText(dialogBuilder.getTitleText());
            title.setTextColor(context.getResources().getColor(dialogBuilder.getTitleColor()));
        }

        cancel.setTextColor(context.getResources().getColor(dialogBuilder.getCancelColor()));

        setItem(datas, list, cancel);


    }

    private void setItem(final List<String> datas, LinearLayout list, TextView cancel) {

        for (int i = 0; i < datas.size(); i++) {
            final int position = i;
            final String text = datas.get(i);
            final TextView tv = new TextView(context);
            tv.setText(text);
            tv.setTag(i);

            int padding = DensityUtils.dp2px(10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(dialogBuilder.getItemlSize());
            tv.setTextColor(context.getResources().getColor(dialogBuilder.getItemColor()));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogBuilder.getDialogListener() != null) {
                        dialogBuilder.getDialogListener().onItemClick(position, text);
                    }
                    //关闭弹窗
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });

            list.addView(tv);
            if (i != datas.size() - 1) {
                View divider = new View(context);
                divider.setBackgroundResource(R.color.color_e1e1e1);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, 3);
                list.addView(divider, params);
            }
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogBuilder.getDialogListener() != null) {
                    dialogBuilder.getDialogListener().onCanceclClick();
                }
                //关闭弹窗
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}
