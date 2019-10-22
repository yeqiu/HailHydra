package com.yeqiu.hydra.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.yeqiu.hydra.HydraUtilsManager;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.constant.Url;
import com.yeqiu.hydra.net.NetIntermediary;
import com.yeqiu.hydra.net.OkGoManager;
import com.yeqiu.hydra.net.callback.dialogcallback.DialogCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/15
 * @describe：
 * @fix：
 */
public class NetActivity extends BaseActivity {

    TextView tvNetPost;
    TextView tvAboutOkgo;
    TextView tvNetResult;

    @Override
    protected Object getContentView() {
        return R.layout.activity_net;
    }

    @Override
    protected void initView() {
        setHeadTitle("网络请求");
        tvNetPost = (TextView) findViewById(R.id.tv_net_post);
        tvNetResult = (TextView) findViewById(R.id.tv_net_result);
        tvAboutOkgo = (TextView) findViewById(R.id.tv_about_okgo);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tvNetPost.setOnClickListener(this);
        tvAboutOkgo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_net_post:
                post();
                break;
            case R.id.tv_about_okgo:
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", Url.about_okgo);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


    private void post() {


        HydraUtilsManager.getInstance().getNetConfig().setNetIntermediary(new NetIntermediary() {
            @Override
            public boolean beforeStart(Request request) {

                /**
                 * 网络请求前会将请求体传递到此方法，可在此方法中加密参数
                 * 在JsonCallback会默认打印请求的地址和参数，
                 * 需要自行打印请参考JsonCallback onStart()
                 * 如不需要打印return true即可
                 * @param request
                 * @return
                 */

                return false;
            }

            @Override
            public boolean beforeResult(Object netData) {

                /**
                 * 网络请求结束后的请求结果会传递到此方法，请求结束后会打印请求结果
                 * 可在此方法中判断业务是否成功
                 * 业务成功 return true; 失败return false;
                 * 注意：return false 会直接走到失败的回调
                 * @param netData
                 * @return
                 */

                return true;
            }
        });


        String url = "https://api.chexixi.com/api/common/sms_send";
        HttpParams params = new HttpParams();
        params.put("mobile", "手机号");
        params.put("action", 1);


        OkGoManager.getInstance().post(url, params, getActivity(), new DialogCallback<String>() {
            @Override
            public void onNetSuccess(String data) {

                tvNetResult.setText(logJson(data));
            }
        });


    }


    public String logJson(String json) {


        String message;
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(4);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(4);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }
        return message;

    }


}
