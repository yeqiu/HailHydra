package com.yeqiu.androidlibrary.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yeqiu.androidlibrary.utils.DensityUtils;
import com.yeqiu.androiddome.R;


/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/16
 * @describe：ios风格dialog
 * @fix：
 */
public class CommonDialog {


    private static Dialog dialog;
    private Activity context;
    private boolean CanceledOnTouchOutside = false;
    private boolean isBackCancel = true;
    private DialogClickListener dialogClickListener;
    private boolean justConfirm = false;

    private String titleText = "标题";
    private String confirmText = "确定";
    private String cancelText = "取消";
    private String contentText = "内容";
    private int confirmColor = R.color.color_808080;
    private int cancelColor = R.color.color_808080;


    public static CommonDialog build(Activity context) {
        dialog = new Dialog(context, R.style.common_dialog);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(DensityUtils.dp2px(context, 30), 0, DensityUtils.dp2px
                (context, 30), 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 0.98f;
        win.setAttributes(lp);
        return new CommonDialog(context);
    }

    public CommonDialog(Activity mContext) {
        this.context = mContext;
    }


    /**
     * 设置标题栏
     *
     * @param titleText
     * @return
     */
    public CommonDialog setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }


    /**
     * 点击外部是否消失
     *
     * @param isTouch
     * @return
     */
    public CommonDialog setCanceledOnTouchOutside(boolean isTouch) {
        this.CanceledOnTouchOutside = isTouch;
        return this;
    }

    /**
     * 点击返回是否消失
     *
     * @param isBackCancel
     * @return
     */
    public CommonDialog setBackCancelable(boolean isBackCancel) {
        this.isBackCancel = isBackCancel;
        return this;
    }


    /**
     * 确定按钮文本
     *
     * @param confirmText
     * @return
     */
    public CommonDialog setConfirmContent(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    /**
     * 取消按钮文本
     *
     * @param confirmText
     * @return
     */
    public CommonDialog setCancelText(String confirmText) {
        this.cancelText = confirmText;
        return this;
    }


    /**
     * 内容文本
     *
     * @param contentText
     * @return
     */
    public CommonDialog setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    /**
     * 确定和取消回调
     *
     * @param dialogClickListener
     * @return
     */
    public CommonDialog setDialogCallBack(DialogClickListener dialogClickListener) {
        this.dialogClickListener = dialogClickListener;
        return this;
    }


    /**
     * 设置仅仅只有确认键
     *
     * @param justConfirm
     * @return
     */
    public CommonDialog setJustConfirm(boolean justConfirm) {
        this.justConfirm = justConfirm;
        return this;
    }

    /**
     * 设置确认按钮颜色
     *
     * @param colorId
     * @return
     */
    public CommonDialog setConfirmColor(int colorId) {
        this.confirmColor = colorId;
        return this;
    }

    /**
     * 设置取消按钮颜色
     *
     * @param colorId
     * @return
     */
    public CommonDialog setCancelColor(int colorId) {
        this.cancelColor = colorId;
        return this;
    }


    /**
     * 显示对话框
     */
    public void show() {
        if (context == null) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_common_dialog, null);

        TextView title = (TextView) view.findViewById(R.id.tv_common_dialog_title);
        TextView content = (TextView) view.findViewById(R.id.tv_common_dialog_content);
        View line = view.findViewById(R.id.v_common_dialog_line);
        TextView cancel = (TextView) view.findViewById(R.id.tv_common_dialog_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_common_dialog_confirm);
        //设置文字
        title.setText(titleText);
        content.setText(contentText);
        cancel.setText(cancelText);
        confirm.setText(confirmText);

        //设置颜色
        confirm.setTextColor(context.getResources().getColor(confirmColor));
        cancel.setTextColor(context.getResources().getColor(cancelColor));

        //设置取消键是否显示
        cancel.setVisibility(justConfirm ? View.GONE : View.VISIBLE);
        line.setVisibility(justConfirm ? View.GONE : View.VISIBLE);

        //设置点击外面 返回是否可以隐藏
        dialog.setCancelable(isBackCancel);
        dialog.setCanceledOnTouchOutside(CanceledOnTouchOutside);
        dialog.setContentView(view);

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

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListener != null) {
                    dialogClickListener.onConfirmClick();
                }
                //关闭弹窗
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        if (!context.isFinishing()) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }


    public interface DialogClickListener {
        void onConfirmClick();

        void onCanceclClick();
    }


}
