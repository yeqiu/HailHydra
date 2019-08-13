package com.yeqiu.hydra.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yeqiu.hydra.utils.LogUtils;
import com.yeqiu.hydra.utils.pay.wxpay.WXPayUtils;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        api = WXAPIFactory.createWXAPI(this, WXPayUtils.getWxPayKey());
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        String errStr = resp.errStr;
        int errCode = resp.errCode;
        LogUtils.i("errStr = "+errStr);
        LogUtils.i("errCode = "+errCode);

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //支付成功
                if (WXPayUtils.getWxPayResultListener() != null) {

                    WXPayUtils.getWxPayResultListener().onPaySuccess();
                    WXPayUtils.UnregisterListener();
                }
                break;
            case BaseResp.ErrCode.ERR_COMM:
                //支付出错
                if (WXPayUtils.getWxPayResultListener() != null) {
                    WXPayUtils.getWxPayResultListener().onPayFail();
                    WXPayUtils.UnregisterListener();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //取消支付
                if (WXPayUtils.getWxPayResultListener() != null) {
                    WXPayUtils.getWxPayResultListener().onPaycancel();
                    WXPayUtils.UnregisterListener();
                }
                break;
            default:
                //未知错误
                if (WXPayUtils.getWxPayResultListener() != null) {
                    WXPayUtils.getWxPayResultListener().onPayFail();
                    WXPayUtils.UnregisterListener();
                }
                break;
        }

        WXPayUtils.unRegisterWeChatPay(WXPayEntryActivity.this);

        finish();
    }
}