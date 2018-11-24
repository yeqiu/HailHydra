
package com.yeqiu.hydrautils.net.callback.dialogcallback;

import android.app.Activity;
import android.text.TextUtils;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.net.callback.jsoncallback.JsonCallback;
import com.yeqiu.hydrautils.utils.UIHelper;
import com.yeqiu.hydrautils.view.dialog.TipDialog;


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
                .setDismissTime(3000)
                .show();

    }

    public DialogCallback(Activity activity) {
        super();
        this.activity = activity;
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);

        try {
            if (dialog != null && !dialog.isShowing() && !activity.isFinishing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSuccess(Response<T> response) {
        T data = response.body();
        if (data == null) {
            onError(0, "数据异常");
        } else {
            onSuccess(data);
        }


    }


    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        //okgo返回错误，可能是连接错误，可能是数据解析错误
        onError(-1, "网络异常");

    }


    /**
     * 网络请求成功回调，保证data!=null
     *
     * @param data
     */
    public abstract void onSuccess(T data);

    /**
     * 网络请求失败回调
     *
     * @param code 0:成功回调的数据=null, -1 网络请求错误
     * @return
     * @parm msg
     */
    public void onError(int code, String msg) {

        if (!TextUtils.isEmpty(msg)) {
            UIHelper.showToast(msg);
        }
    }


}
