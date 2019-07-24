package com.yeqiu.hydra.utils;


import com.yeqiu.hydra.BuildConfig;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/06/25
 * @describe：
 * @fix：
 */
public class AppUtils {


    /**
     * 当前是否是debug环境
     * @return
     */
    public static boolean isDebug() {


        return BuildConfig.DEBUG;
    }



}
