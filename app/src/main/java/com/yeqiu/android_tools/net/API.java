package com.yeqiu.android_tools.net;

import com.yeqiu.androiddome.BuildConfig;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/14 下午4:41
 * @describe：存放接口的地址
 * @fix：
 */
public class API {

    //正式环境
    public static String OFFICIAL_BASE_URL = "http://api.xmfenqi.cn/";
    //测试环境
    public static String TEST_BASE_URL = "http://test.api.xmfenqi.cn:7881/";
    //当前app访问的网络环境
    public static String BASE_URL = BuildConfig.DEBUG ? TEST_BASE_URL : OFFICIAL_BASE_URL;








}
