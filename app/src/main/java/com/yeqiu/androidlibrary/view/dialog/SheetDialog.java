package com.yeqiu.androidlibrary.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.androidlibrary.utils.DensityUtils;
import com.yeqiu.androiddome.R;

import java.util.List;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/17
 * @describe：仿ios底部弹出dialog
 * @fix：
 */
public class SheetDialog {

    private final Activity context;
    private String titleText;
    private List<String> datas;

    private boolean CanceledOnTouchOutside = true;
    private boolean isBackCancel = true;
    private DialogClickListener dialogClickListener;
    private static Dialog dialog;
    private int titleColor = R.color.color_1a1a1a;
    private int cancelColor = R.color.color_1a1a1a;
    private int itemColor = R.color.color_1a1a1a;

    public static SheetDialog build(Activity context) {

        dialog = new Dialog(context, R.style.sheet_dialog);
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(DensityUtils.dp2px(context, 10), 0, DensityUtils.dp2px
                (context, 10), DensityUtils.dp2px(context, 10));
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 0.98f;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);
        return new SheetDialog(context);
    }

    public SheetDialog(Activity mContext) {
        this.context = mContext;
    }

    /**
     * 设置标题栏
     *
     * @param titleText
     * @return
     */
    public SheetDialog setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }


    /**
     * 设置item
     *
     * @return
     */
    public SheetDialog setItem(List<String> datas) {
        this.datas = datas;
        return this;
    }


    /**
     * 点击外部是否消失
     *
     * @param isTouch
     * @return
     */
    public SheetDialog setCanceledOnTouchOutside(boolean isTouch) {
        this.CanceledOnTouchOutside = isTouch;
        return this;
    }

    /**
     * 点击返回是否消失
     *
     * @param isBackCancel
     * @return
     */
    public SheetDialog setBackCancelable(boolean isBackCancel) {
        this.isBackCancel = isBackCancel;
        return this;
    }

    /**
     * 设置确认按钮颜色
     *
     * @param colorId
     * @return
     */
    public SheetDialog setTitleColor(int colorId) {
        this.titleColor = colorId;
        return this;
    }

    /**
     * 设置取消按钮颜色
     *
     * @param colorId
     * @return
     */
    public SheetDialog setCancelColor(int colorId) {
        this.cancelColor = colorId;
        return this;
    }

    /**
     * 设置item颜色
     *
     * @param colorId
     * @return
     */
    public SheetDialog setItemColor(int colorId) {
        this.itemColor = colorId;
        return this;
    }


    /**
     * 回调点击监听
     *
     * @param dialogClickListener
     * @return
     */
    public SheetDialog setDialogClickListener(DialogClickListener dialogClickListener) {
        this.dialogClickListener = dialogClickListener;
        return this;
    }


    public void show() {
        if (context == null) {
            return;
        }

        View view = LayoutInflater.from(context).inflate(R.layout.layout_sheet_dialog, null);

        TextView title = (TextView) view.findViewById(R.id.tv_sheet_dialog_title);
        LinearLayout list = (LinearLayout) view.findViewById(R.id.ll_sheet_dialog_list);
        TextView cancel = (TextView) view.findViewById(R.id.tv_sheet_dialog_cancel);


        if (datas == null || datas.size() == 0) {
            return;
        }

        if (TextUtils.isEmpty(titleText)) {
            //不显示标题
            title.setVisibility(View.GONE);
        } else {
            title.setText(titleText);
            title.setTextColor(context.getResources().getColor(titleColor));
        }

        cancel.setTextColor(context.getResources().getColor(cancelColor));

        for (int i = 0; i < datas.size(); i++) {
            final int position = i;
            TextView tv = new TextView(context);
            tv.setText(datas.get(i));
            tv.setTag(i);

            int padding = DensityUtils.dp2px(context, 10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setTextColor(context.getResources().getColor(itemColor));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogClickListener != null) {
                        dialogClickListener.onItemClick(position);
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
                if (dialogClickListener != null) {
                    dialogClickListener.onCanceclClick();
                }
                //关闭弹窗
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });


        dialog.setCancelable(isBackCancel);
        dialog.setCanceledOnTouchOutside(CanceledOnTouchOutside);
        dialog.setContentView(view);

        if (!context.isFinishing()) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }


    public interface DialogClickListener {
        void onItemClick(int position);

        void onCanceclClick();
    }


}
