package com.yeqiu.hydra.net;

import com.lzy.okgo.request.base.Request;

import java.util.Map;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/06/25
 * @describe：网络中间层 可以处理请求前的数据 注意 这里做的所有操作都不会影响网络请求的原数据
 * @fix：
 */
public interface NetIntermediary {


    /**
     * 网络请求前会将请求体传递到此方法，请求前会打印所有的参数。
     * 可在此方法中剔除重复的公参
     * @param request
     * @return
     */
    Map<String, String> afterStart(Request request);



    /**
     * 网络请求结束后的请求结果会传递到此方法，请求结束后会打印请求结果
     * 可在此方法中判断业务是否成功
     * @param netRestut
     */
    void afterResult(String netRestut);

}
