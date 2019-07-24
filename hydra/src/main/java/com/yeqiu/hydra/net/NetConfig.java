package com.yeqiu.hydra.net;

import android.app.Application;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/06/25
 * @describe：
 * @fix：
 */
public class NetConfig {


    private static NetConfig instance;
    private NetIntermediary netIntermediary;

    private NetConfig() {
        instance = this;
    }

    public static synchronized NetConfig getInstance() {
        if (instance == null) {
            instance = new NetConfig();
        }
        return instance;
    }


    public NetConfig setNetIntermediary(NetIntermediary netIntermediary){

        this.netIntermediary = netIntermediary;

        return getInstance();
    }


    public NetIntermediary getNetIntermediary() {
        return netIntermediary;
    }



    /**
     * 初始化网络框架
     *
     * @param application
     */
    public NetConfig init(Application application) {
        OkGoManager.getInstance().init(application);
        return getInstance();
    }


    /**
     * 添加请求头
     *
     * @param headers
     */
    public NetConfig setHttpHeaders(HttpHeaders headers) {

        OkGoManager.getInstance().setHttpHeaders(headers);
        return getInstance();
    }



    /**
     * 添加公参
     *
     * @param params
     * @return
     */
    public NetConfig setCommonParams(HttpParams params) {

        OkGoManager.getInstance().setCommonParams(params);
        return getInstance();
    }



    /**
     * 获取公共请求头
     */
    public HttpHeaders getCommonHeaders(){
      return   OkGoManager.getInstance().getCommonHeaders();
    }

    /**
     * 获取共参
     */
    public HttpParams getCommonParams(){

       return OkGoManager.getInstance().getCommonParams();
    }






}
