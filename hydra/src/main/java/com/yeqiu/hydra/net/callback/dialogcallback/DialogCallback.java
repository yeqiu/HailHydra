
package com.yeqiu.hydra.net.callback.dialogcallback;

import android.app.Activity;

import com.yeqiu.hydra.net.callback.jsoncallback.JsonCallback;
import com.yeqiu.hydra.utils.ActivityManager;
import com.yeqiu.hydra.view.dialog.TipDialog;
import com.yeqiu.hydrautils.R;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    private TipDialog dialog;
    private Activity activity;

    private void initDialog(Activity activity) {

        dialog = (TipDialog) new TipDialog(activity)
                .build()
                .setTipText("正在加载")
                .setIconId(R.drawable.icon_load)
                .setOrientationHorizontal(false)
                .setIsLoading(true)
                .show();

    }

    public DialogCallback(Activity activity) {
        super();
        this.activity = activity;
        initDialog(activity);
    }

    public DialogCallback() {

        this.activity = ActivityManager.getAppManager().getCurrentActivity();

        initDialog(activity);

    }


    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null ) {
            dialog.dismissDialog();
        }
    }




}
