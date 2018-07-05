package com.yeqiu.androidlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yeqiu.androiddome.R;
import com.yeqiu.androidlibrary.net.OkManager;
import com.yeqiu.androidlibrary.net.callback.dialogcallback.DialogCallback;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/5
 * @describe：
 * @fix：
 */
public class OKGoActivity extends AppCompatActivity {

    private Button request;
    private TextView result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okgo);

        request = (Button) findViewById(R.id.bt_okgo_request);
        result = (TextView) findViewById(R.id.tv_okgo_result);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    private void request() {
        OkManager.getInstance().init(getApplication(), null);

        OkManager.getInstance().test(true, new DialogCallback<String>(this) {
            @Override
            public void onSuccess(String data) {
                result.setText(data);
            }

            @Override
            public void onError(int code, String msg) {
                super.onError(code, msg);
                result.setText(msg);

            }
        });
    }
}
