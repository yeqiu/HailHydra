package com.yeqiu.hydrautils.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ye
 * @date 2018/4/2
 * @desc string 工具类
 */
public class StringUtils {

    /**
     * 金额 格式化
     */
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("###,###,###,##0.00");

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
     * 验证是否是手机
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
     * 验证是否是身份证
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
     * 验证是否是数字
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
     * 验证是否是邮箱
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
     * 验证是否是数字+字母
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
     * 获取当前日期
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss ");
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当前日期
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
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


    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是双精度浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return value.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * 隐藏手机中间4位号码
     * 130****0000
     *
     * @param mobile_phone 手机号码
     * @return 130****0000
     */
    public static String hideMobilePhone(String mobile_phone) {
        if (mobile_phone.length() != 11) {
            return "手机号码不正确";
        }
        return mobile_phone.substring(0, 3) + "****" + mobile_phone.substring(7, 11);
    }

    /**
     * 格式化银行卡 加*
     * 3749 **** **** 330
     *
     * @param cardNo 银行卡
     * @return 3749 **** **** 330
     */
    public static String formatCard(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card = cardNo.substring(0, 4) + " **** **** ";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 获取银行卡后四位
     *
     * @param cardNo
     * @return
     */
    public static String getCardEnd(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 金额格式化
     *
     * @param value 数值
     * @return
     */
    public static String getAmountValue(String value) {
        if (isEmpty(value)) {
            return "0";
        }
        return AMOUNT_FORMAT.format(Double.parseDouble(value));
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }


    /**
     * 反转字符串 倒序
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }


}
