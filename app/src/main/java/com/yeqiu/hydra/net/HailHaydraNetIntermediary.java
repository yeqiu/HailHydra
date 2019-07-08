package com.yeqiu.hydra.net;

import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydra.net.bean.BaseBean;
import com.yeqiu.hydra.utils.GsonUtils;
import com.yeqiu.hydra.utils.LogUtils;
import com.yeqiu.hydra.utils.UIHelper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class HailHaydraNetIntermediary implements NetIntermediary {


    @Override
        public Request beforeStart(Request request) {


        //示例 取出参数中mobile的值 修改为222
        LinkedHashMap<String, List<String>> params = request.getParams().urlParamsMap;
        List<String> strings = params.get("mobile");
        if (strings==null||strings.size()==0){
            return request;
        }
        request.getParams().put("mobile","222");

        return request;
    }

    @Override
    public boolean beforeResult( String netData) {


        BaseBean baseBean = GsonUtils.jsonTobean( netData,BaseBean.class);




        switch (baseBean.getCode()) {

            case 200:
                //请求成功
                LogUtils.i("请求成功");
                return true;

            case 401:
                //登录失效
                UIHelper.showToast("登录失效");
                LogUtils.i("登录失效");
                return false;


            default:
                LogUtils.i("请求失败");
                UIHelper.showToast(baseBean.getMessage());
                return false;
        }

    }
}
