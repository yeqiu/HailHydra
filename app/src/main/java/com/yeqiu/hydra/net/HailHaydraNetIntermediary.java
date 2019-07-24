package com.yeqiu.hydra.net;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydra.net.bean.BaseBean;
import com.yeqiu.hydra.utils.NetLog;
import com.yeqiu.hydra.utils.ToastUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class HailHaydraNetIntermediary implements NetIntermediary {


    @Override
    public boolean beforeStart(Request request) {

        HttpParams params = request.getParams();
        Map<String, String> map = new HashMap<>();
        LinkedHashMap<String, List<String>> urlParamsMap = params.urlParamsMap;
        Set<String> strings = urlParamsMap.keySet();
        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            List<String> strings1 = urlParamsMap.get(next);
            if (strings1 != null && strings1.size() > 0) {
                map.put(next, strings1.get(0));
            }
        }

        HttpHeaders headers = request.getHeaders();
        String authToken = headers.get("token");
        map.put("token", authToken);
        NetLog.logUrl(request.getUrl(), new Gson().toJson(map));

        return true;
    }


    @Override
    public boolean beforeResult(Object netData) {

        BaseBean data = (BaseBean) netData;

        switch (data.getCode()) {

            case 200:
                return true;

            case 401:
                ToastUtils.showToast("登录失效，请重新登录");
                return false;

            default:
                ToastUtils.showToast(data.getMessage());
                return false;
        }
    }

}
