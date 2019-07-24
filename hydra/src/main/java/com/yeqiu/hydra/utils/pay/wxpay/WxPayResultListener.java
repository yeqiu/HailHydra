package com.yeqiu.hydra.utils.pay.wxpay;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/8
 * @describe：
 * @fix：
 */
public interface WxPayResultListener {


    void onPaySuccess();

    void onPayFail();

    void onPaycancel();


}
