package com.yeqiu.androidlibrary.utils;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * @author ye
 * @date 2018/4/2
 * @desc string 工具类
 */
public class StringUtils {

    /**
     * 字符串判空
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }


    /**
     * 是否有小数
     */
    public static boolean isHaveDecimal(String string) {
        if (string.contains(".")) {
            return true;
        }
        return false;
    }

    /**
     * 关键字变色
     */
    public static CharSequence matcherSearchText(int color, String string, String keyWord) {
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        int indexOf = string.indexOf(keyWord);
        if (indexOf != -1) {
            builder.setSpan(new ForegroundColorSpan(color), indexOf, indexOf + keyWord.length(),
                    SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }


    /**
     * 验证手机格式
     */
    public static boolean isPhoneMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通） 1743
    第一位为1，第二位为3或5或8 7，其他位置的可以为0-9
    */
        String num = "[1][34578]\\d{9}";
        if (isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }
    /**
     * 身份证号格式
     */
    public static boolean isIdCardNo(String idCard) {

        String regx = "[0-9]{17}x|X";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        if (isEmpty(idCard)) {
            return false;
        } else {
            return idCard.matches(regx) || idCard.matches(reg1) || idCard.matches(regex);
        }
    }
    /**
     * 身份证号格式
     */
    public static boolean isNumber(String text) {

        String regx = "^-?[0-9]\\d*$";

        if (isEmpty(text)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return text.matches(regx);
        }
    }


    /**
     * 邮箱验证格式
     */
    public static boolean isEmailMobile(String idCard) {
        String email = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(" +
                "([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if (isEmpty(idCard)) {
            return false;
        } else {
            return idCard.matches(email);
        }
    }

    /**
     * 字母数字校验
     */
    public static boolean isPassWord(String passWord) {
        String isPassWord = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";

        if (isEmpty(passWord)) {
            return false;
        } else {
            return passWord.matches(isPassWord);
        }
    }


    /**
     * 获取当前时间
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当前日期
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 格式化数字 保留小数点后2位
     */
    public static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat("######0.00");

        String format = df.format(number);
        return format;


    }
}
