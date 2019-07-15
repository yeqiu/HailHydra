
package com.yeqiu.hailhaydra.net.callback.dialogcallback;

import android.app.Activity;

import com.lzy.okgo.request.base.Request;
import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.net.callback.jsoncallback.JsonCallback;
import com.yeqiu.hydra.utils.ActivityManager;
import com.yeqiu.hydra.view.dialog.TipDialog;


/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16
 * @describe：弹出等待框的网络请求回调
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
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);

    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null ) {
            dialog.dismissDialogWhitDelayer();
        }
    }




}
