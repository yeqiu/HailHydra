package com.yeqiu.androidlibrary.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.yeqiu.androidlibrary.utils.DensityUtils;
import com.yeqiu.androiddome.R;


/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/5/2
 * @describe：带输入框的dailog
 * @fix：
 */
public class EditDialog  {


    private String hintText;
    private EditDialogClickListener dialogClickListener;
    private static Dialog dialog;
    private  Activity context;
    private boolean CanceledOnTouchOutside = false;
    private boolean isBackCancel = true;
    private boolean justConfirm = false;

    private String titleText = "标题";
    private String confirmText = "确定";
    private String cancelText = "取消";
    private int confirmColor = R.color.color_808080;
    private int cancelColor = R.color.color_808080;
    private int inputType = InputType.TYPE_CLASS_TEXT;


    public static EditDialog build(Activity context) {
        dialog = new Dialog(context, R.style.common_dialog);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(DensityUtils.dp2px(context, 30), 0, DensityUtils.dp2px
                (context, 30), 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 0.98f;
        win.setAttributes(lp);
        return new EditDialog(context);
    }

    public EditDialog(Activity mContext) {
        this.context = mContext;
    }






    /**
     * 设置标题栏
     *
     * @param titleText
     * @return
     */
    public EditDialog setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }
   /**
     * 设置输入的文本格式
     *
     * @param inputType
     * @return
     */
    public EditDialog setInputType(int inputType) {
        this.inputType = inputType;
        return this;
    }


    /**
     * 点击外部是否消失
     *
     * @param isTouch
     * @return
     */
    public EditDialog setCanceledOnTouchOutside(boolean isTouch) {
        this.CanceledOnTouchOutside = isTouch;
        return this;
    }

    /**
     * 点击返回是否消失
     *
     * @param isBackCancel
     * @return
     */
    public EditDialog setBackCancelable(boolean isBackCancel) {
        this.isBackCancel = isBackCancel;
        return this;
    }


    /**
     * 确定按钮文本
     *
     * @param confirmText
     * @return
     */
    public EditDialog setConfirmContent(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    /**
     * 取消按钮文本
     *
     * @param confirmText
     * @return
     */
    public EditDialog setCancelText(String confirmText) {
        this.cancelText = confirmText;
        return this;
    }



    /**
     * 设置仅仅只有确认键
     *
     * @param justConfirm
     * @return
     */
    public EditDialog setJustConfirm(boolean justConfirm) {
        this.justConfirm = justConfirm;
        return this;
    }

    /**
     * 设置确认按钮颜色
     *
     * @param colorId
     * @return
     */
    public EditDialog setConfirmColor(int colorId) {
        this.confirmColor = colorId;
        return this;
    }

    /**
     * 设置取消按钮颜色
     *
     * @param colorId
     * @return
     */
    public EditDialog setCancelColor(int colorId) {
        this.cancelColor = colorId;
        return this;
    }








    public EditDialog setHint(String hintText) {

        this.hintText = hintText;
        return this;
    }


    public EditDialog setDialogCallBack(EditDialogClickListener dialogClickListener) {

        this.dialogClickListener = dialogClickListener;
        return this;
    }



    public void show() {
        if (context == null || dialog == null) {
            return;
        }

        View view = LayoutInflater.from(context).inflate(R.layout.layout_edit_dialog, null);
        TextView title = (TextView) view.findViewById(R.id.tv_edit_dialog_title);
        final EditText edit = (EditText) view.findViewById(R.id.et_edit_dialog_edit);
        View line = view.findViewById(R.id.v_edit_dialog_line);
        TextView cancel = (TextView) view.findViewById(R.id.tv_edit_dialog_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_edit_dialog_confirm);
        //设置文字
        title.setText(titleText);
        edit.setHint(hintText);
        cancel.setText(cancelText);
        confirm.setText(confirmText);

        edit.setInputType(inputType);

        //设置颜色
        confirm.setTextColor(context.getResources().getColor(confirmColor));
        cancel.setTextColor(confirm.getResources().getColor(cancelColor));

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
                    dialogClickListener.onCanceclClick(edit.getText().toString());
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


                //校验callback
                if (dialogClickListener != null) {
                    dialogClickListener.onConfirmClick(edit.getText().toString());
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


    public interface EditDialogClickListener {
        void onConfirmClick(String inputText);

        void onCanceclClick(String inputText);
    }
}
