package com.yeqiu.hailhaydra.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.net.NetManager;
import com.yeqiu.hailhaydra.net.callback.dialogcallback.DialogCallback;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/23
 * @describe：
 * @fix：
 */
public class NetActivity extends BaseActivity {

    private Button btGetNet;
    private TextView tvNetData;

    @Override
    protected Object getContentView() {
        return R.layout.activity_net;
    }

    @Override
    protected void initView() {
        btGetNet = (Button) findViewById(R.id.bt_get_net);
        tvNetData = (TextView) findViewById(R.id.tv_net_data);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btGetNet.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_net:
                getNetData();
                break;

            default:
                break;
        }
    }


    private void getNetData() {

        NetManager.getInstance().getMachineList(context, new DialogCallback<String>(context) {
            @Override
            public void onNetSuccess(String data) {

                tvNetData.setText("网络数据"+"\n"+data);
            }
        });
    }
}
