package com.yeqiu.hailhaydra.utils;

import android.text.TextUtils;

import com.yeqiu.hailhaydra.net.API;
import com.yeqiu.hydra.utils.StringUtils;
import com.yeqiu.hydra.utils.UIHelper;

import java.text.DecimalFormat;

/**
 * @project：ZebraFQ
 * @author：小卷子
 * @date 2018/12/22
 * @describe：
 * @fix：
 */
public class AppUtils {



    /**
     * 判断密码 8-20位 数字字母组合
     *
     * @param psw
     * @return
     */
    public static boolean checkPsw(String psw) {
        if (TextUtils.isEmpty(psw)) {
            UIHelper.showToast("请先输入密码");
            return false;
        }

        if (!isPassWord(psw)) {
            UIHelper.showToast("您输入密码不符合规范");
            return false;
        }

        return true;
    }


    /**
     * 8到20位
     * 字母数字校验
     */
    public static boolean isPassWord(String passWord) {
        String isPassWord = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";

        if (TextUtils.isEmpty(passWord)) {
            return false;
        } else {
            return passWord.matches(isPassWord);
        }


    }


    /**
     * 校验身份证号
     *
     * @param id
     * @return
     */
    public static boolean checkId(String id) {
        if (TextUtils.isEmpty(id)) {
            UIHelper.showToast("请先输入身份证");
            return false;
        }

        if (!StringUtils.isIdCardNo(id)) {
            UIHelper.showToast("请输入正确的身份证");
            return false;
        }

        return true;

    }


    /**
     * 校验网络请求是否成功 code==200
     *
     * @param code
     * @return
     */
    public static boolean checkNetCode(int code) {

        if (code == API.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 退出登录清除用户信息
     */
    public static void logout() {

        // TODO: 2019/4/23 退出登录



    }

    /**
     * 校验是否登录
     *
     * @param code
     */
    public static void checkLogin(final String code) {

        if (TextUtils.equals(API.NOT_LOGIN, code)) {
            //登录失败
            UIHelper.showToast("登录失效，请重新登录");
            //清除登录信息
            logout();

        }


    }

    /**
     * 保存登录时的数据
     *
     */
    public static void savaLoginData() {


        // TODO: 2019/4/23 保存登录时的数据

    }


    /**
     * 获取登录状态
     * token不为空认为是登录状态
     *
     * @return
     */
    public static boolean isLogin() {

        // TODO: 2019/4/23  获取登录状态

        return true;


    }


    /**
     * 检查知否登录，如果没登录会自动跳转到登录页面
     *
     * @return
     */
    public static boolean checkLogin() {
        if (isLogin()) {
            return true;
        } else {
            //去登录
            UIHelper.showToast("请先登录");
            // TODO: 2019/4/23 跳转去登录页

            return false;
        }
    }


    /**
     * 0 1 转boolean
     *
     * @param oneOrZero
     * @return
     */
    public static boolean stringToBoolean(String oneOrZero) {

        return TextUtils.equals("1", oneOrZero);

    }


    public static String booleanToString(boolean b) {

        return b ? "1" : "0";
    }


    /**
     * 0 1 转boolean
     *
     * @param oneOrZero
     * @return
     */
    public static boolean intToBoolean(int oneOrZero) {

        return 1 == oneOrZero;

    }


    /**
     * 获取用户token
     *
     * @return
     */
    public static String getToken() {

        // TODO: 2019/4/23  获取用户token
        return "";
    }


    /**
     * 金额三位隔开
     */
    public static String FormatAmount(int number) {
        DecimalFormat df = new DecimalFormat("#,###");
        String format = df.format(number);

        return format;
    }



}
