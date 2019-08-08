package com.yeqiu.hydra.utils.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * @author ye
 * @date 2017/12/26
 * @desc 支付宝工具类
 */
public class ALiPayUtils {

    private static final int SDK_PAY_FLAG = 1;
    private static OnPayResultListener onPayResultListener;

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




    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case SDK_PAY_FLAG:
                    //支付

                    getAliPayResult(msg);

                    break;


                default:
                    break;
            }
        }
    };



    private static void getAliPayResult(Message msg) {

        Map<String, String> obj = (Map<String, String>) msg.obj;
        PayResult payResult = new PayResult(obj);
        String resultInfo = payResult.getResult();
        String resultStatus = payResult.getResultStatus();

        if (TextUtils.equals(resultStatus, "9000") || (TextUtils.equals(resultStatus, "8000"))) {
            //支付宝返回成功
            if (onPayResultListener != null) {
                onPayResultListener.onAliPaySuccess(resultInfo);
            }

        } else {
            //支付宝返回失败
            if (onPayResultListener != null) {
                onPayResultListener.onAliPayFail(resultStatus, payResult.getMemo());
            }
        }
    }


    public interface OnPayResultListener {

        void onAliPaySuccess(String resultInfo);

        void onAliPayFail(String code, String msg);
    }


}
