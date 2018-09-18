package com.yeqiu.hydrautils.common;

import android.text.TextUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/18
 * @describe：其他的工具类
 * @fix：
 */
public class HydraUtils {

    /**
     * 0 1 转boolean
     *
     * @param oneOrZero
     * @return
     */
    public static boolean stringToBoolean(String oneOrZero) {

        return TextUtils.equals("1", oneOrZero);

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


}
