package com.yeqiu.hydra.utils.pay.wxpay;

import android.content.Context;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yeqiu.hydra.utils.ToastUtils;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-08-05
 * @describe：微信支付
 * @fix：
 */
public class WXPayUtils {


    private static WxPayResultListener wxPayResultListener;
    private static Context context;
    private static String appKey;

    public static WxPayResultListener getWxPayResultListener() {
        return wxPayResultListener;
    }

    public static void setWxPayResultListener(WxPayResultListener wxPayResultListener) {
        WXPayUtils.wxPayResultListener = wxPayResultListener;
    }

    public static String getAppKey() {
        return appKey == null ? "" : appKey;
    }

    public static void unregisterListener() {

        WXPayUtils.wxPayResultListener = null;
        unRegisterWeChatPay(context,appKey);
    }


    /**
     * 注册微信支付
     */
    private static void registerWeChatPay(Context context, String appKey) {

        getWXApi(context,appKey).registerApp(appKey);

    }

    /**
     * 注销微信支付
     */
    private static void unRegisterWeChatPay(Context context, String appKey) {
        getWXApi(context,appKey).unregisterApp();

    }

    /**
     * 获取IWXAPI
     */
    private static IWXAPI getWXApi(Context context,String appKey) {

        return WXAPIFactory.createWXAPI(context, appKey, true);
    }

    /**
     * 创建微信支付支付实体类
     *
     * @param appId
     * @param partnerId 商户号
     * @param prepayId  预支付交易会话ID
     * @param nonceStr  随机字符串
     * @param timeStamp 时间戳
     * @param sign      签名
     * @return
     */
    private static PayReq createPayReq(String appId, String partnerId, String prepayId, String
            nonceStr, String timeStamp, String sign) {
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.packageValue = "Sign=WXPay";
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;
        return request;
    }

    /**
     * 支付
     *
     * @param context   支付页面上下文
     * @param partnerId 商户号
     * @param prepayId  预支付交易会话ID
     * @param nonceStr  随机字符串
     * @param timeStamp 时间戳
     * @param sign      签名
     */
    public static void doPay(Context context,String appKey, String appId, String partnerId, String
            prepayId, String nonceStr, String timeStamp, String sign) {

        WXPayUtils.context = context;
        WXPayUtils.appKey = appKey;

        if (!checkUserWeChat(context,appKey)) {
            ToastUtils.showToast("请检查是否安装微信或微信版本是否支持支付");
            return;
        }
        registerWeChatPay(context,appKey);

        PayReq req = createPayReq(appId, partnerId, prepayId, nonceStr, timeStamp, sign);
        getWXApi(context,appKey).sendReq(req);
    }

    /**
     * 获取用户设备是否安装微信APP
     */
    public static boolean checkUserWeChat(Context context,String appKey) {

        boolean isPaySupported = getWXApi(context,appKey).getWXAppSupportAPI() >= Build
                .PAY_SUPPORTED_SDK_INT && getWXApi(context,appKey).isWXAppInstalled();
        return isPaySupported;
    }
}
