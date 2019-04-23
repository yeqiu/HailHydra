package com.yeqiu.hailhaydra.net;


import com.yeqiu.hailhaydra.BuildConfig;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14 下午4:41
 * @describe：存放接口的地址
 * @fix：
 */
public interface API {


    int SUCCESS_CODE = 200;
    String NOT_LOGIN = "401";

    /**
     * 正式环境
     */
    String OFFICIAL_BASE_URL = "https://api.chexixi.com/api/";


    /**
     * 测试环境
     */
    String TEST_BASE_URL = "https://cxx.xmfenqi.cn/api/";


    /**
     * 当前app访问的网络环境
     */
    String BASE_URL = BuildConfig.DEBUG ? TEST_BASE_URL : OFFICIAL_BASE_URL;


    /**
     * 站点列表接口
     */
    String machine_list = BASE_URL + "home/machine/list";



}
