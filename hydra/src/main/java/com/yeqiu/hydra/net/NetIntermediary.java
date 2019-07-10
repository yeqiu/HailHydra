package com.yeqiu.hydra.net;

import com.lzy.okgo.request.base.Request;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/06/25
 * @describe：网络中间层 可以处理请求前的数据 注意 这里做的所有操作都不会影响网络请求的原数据
 * @fix：
 */
public interface NetIntermediary<T> {


    /**
     * 网络请求前会将请求体传递到此方法，可在此方法中加密参数
     * 在JsonCallback会默认打印请求的地址和参数，
     * 需要自行打印请参考JsonCallback onStart()
     * 如不需要打印return true即可
     * @param request
     * @return
     */
    boolean beforeStart(Request request);



    /**
     * 网络请求结束后的请求结果会传递到此方法，请求结束后会打印请求结果
     * 可在此方法中判断业务是否成功
     * 业务成功 return true; 失败return false;
     * 注意：return false 会直接走到失败的回调
     * @param netData
     * @return
     */
    boolean beforeResult(T netData);

}
