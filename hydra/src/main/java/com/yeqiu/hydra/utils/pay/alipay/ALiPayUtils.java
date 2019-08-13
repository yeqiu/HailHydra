package com.yeqiu.hydra.utils.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * @author ye
 * @date 2017/12/26
 * @desc 支付宝工具类
 */
public class ALiPayUtils {
    private static final int SDK_PAY_FLAG = 1;  //支付码
    private static final int SDK_AUTH_FLAG = 2; //授权码
    private static OnPayResultListener onPayResultListener;
    private static OnLoginResultListener onLoginResultListener;

    public static void pay(final String orderInfo, final Activity activity, OnPayResultListener
            onPayResultListener) {

        ALiPayUtils.onPayResultListener = onPayResultListener;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    public static void login(final String authInfo, final Activity activity,
                             OnLoginResultListener onLoginResultListener) {

        ALiPayUtils.onLoginResultListener = onLoginResultListener;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                AuthTask authTask = new AuthTask(activity);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case SDK_PAY_FLAG:
                    //支付

                    getAliPayResult(msg);

                    break;

                case SDK_AUTH_FLAG:
                    //授权
                    getAliPayAUTH(msg);

                    break;

                default:
                    break;
            }
        }
    };

    private static void getAliPayAUTH(Message msg) {

        //支付宝登录返回
        AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
        String resultStatus = authResult.getResultStatus();

        // 判断resultStatus 为“9000”且result_code
        // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
        if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult
                .getResultCode(), "200")) {
            // 获取alipay_open_id，调支付时作为参数extern_token 的value传入，则支付账户为该授权账户
            if (onLoginResultListener != null) {
                onLoginResultListener.OnLoginSuccess(authResult.getAuthCode());
            }


        } else {
            // 其他状态值则为授权失败
            if (onLoginResultListener != null) {
                onLoginResultListener.OnLoginFail("-1", authResult.getAuthCode());
            }

        }

    }

    private static void getAliPayResult(Message msg) {

        Map<String, String> obj = (Map<String, String>) msg.obj;
        PayResult payResult = new PayResult(obj);
        String resultInfo = payResult.getResult();
        String resultStatus = payResult.getResultStatus();

        if (TextUtils.equals(resultStatus, "9000") || (TextUtils.equals(resultStatus, "8000"))) {
            //支付宝返回成功
            if (onPayResultListener != null) {
                onPayResultListener.OnAliPaySuccess(resultInfo);
            }

        } else {
            //支付宝返回失败
            if (onPayResultListener != null) {
                onPayResultListener.OnAliPayFail(resultStatus, payResult.getMemo());
            }
        }
    }


    public interface OnPayResultListener {

        void OnAliPaySuccess(String resultInfo);

        void OnAliPayFail(String ret_code, String msg);
    }

    public interface OnLoginResultListener {

        void OnLoginSuccess(String data);

        void OnLoginFail(String ret_code, String msg);
    }
}
