package com.yeqiu.android_tools.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yeqiu.android_tools.receiver.NetWorkStateReceiver;
import com.yeqiu.android_tools.utils.NetWorkUtils;
import com.yeqiu.androiddome.R;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/6/1
 * @describe：
 * @fix：
 */

/**
 * 权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 */

public class NetworkListenerActivity extends AppCompatActivity {

    private TextView netStatus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networklistener);
        netStatus = (TextView) findViewById(R.id.net_status);


        NetWorkUtils.turnOnListnerWithState(NetworkListenerActivity.this, new
                NetWorkStateReceiver.OnNetWorkStateChangeListener() {


                    @Override
                    public void onMobile() {
                        netStatus.setText("当前网络：流量");
                    }

                    @Override
                    public void onWifi() {
                        netStatus.setText("当前网络：wifi");
                    }

                    @Override
                    public void onNoNet() {
                        netStatus.setText("当前网络：无网络");
                    }
                });


    }


    protected void onDestroy() {
        super.onDestroy();
        NetWorkUtils.turnOffListner();

    }


}
