package com.yeqiu.hydra.net;

import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydra.net.NetIntermediary;

import java.util.Map;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class HailHaydraNetIntermediary implements NetIntermediary {


    @Override
    public Map<String, String> afterStart(Request request) {
        return null;
    }

    @Override
    public void afterResult(String netRestut) {

    }
}
