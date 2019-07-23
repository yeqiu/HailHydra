package com.yeqiu.hydra.view.activity;

import android.Manifest;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.PhoneInfoUtil;

import java.util.List;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/13
 * @describe：
 * @fix：
 */
public class PhoneInfoActivity extends BaseActivity {


    TextView tvDeviceIsPhone;
    TextView tvDeviceSdkVersion;
    TextView tvDeviceModel;
    TextView tvDeviceIemi;
    TextView tvDevicePhoneNumber;
    TextView tvDeviceGsp;
    TextView tvDeviceFrom;
    TextView tvDeviceOperatorName;
    TextView tvDeviceNet;


    @Override
    protected Object getContentView() {
        return R.layout.activity_phone_info;
    }

    @Override
    protected void initView() {

        setHeaderTitle("获取本机信息");
        tvDeviceIsPhone = (TextView) findViewById(R.id.tv_device_is_phone);
        tvDeviceSdkVersion = (TextView) findViewById(R.id.tv_device_sdk_version);
        tvDeviceModel = (TextView) findViewById(R.id.tv_device_model);
        tvDeviceIemi = (TextView) findViewById(R.id.tv_device_iemi);
        tvDevicePhoneNumber = (TextView) findViewById(R.id.tv_device_phone_number);
        tvDeviceGsp = (TextView) findViewById(R.id.tv_device_gsp);
        tvDeviceFrom = (TextView) findViewById(R.id.tv_device_from);
        tvDeviceOperatorName = (TextView) findViewById(R.id.tv_device_operator_name);
        tvDeviceNet = (TextView) findViewById(R.id.tv_device_net);
    }


    /**
     * 权限
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     */
    @Override
    protected void initData() {


        String permissions[] = {Manifest.permission.READ_PHONE_STATE};

        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {

                        setData();

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        setData();
                    }
                })
                .start();


    }

    private void setData() {

        tvDeviceIsPhone.setText(PhoneInfoUtil.isPhone() + "");
        tvDeviceSdkVersion.setText(PhoneInfoUtil.getSDKVersion() + "");
        tvDeviceModel.setText(PhoneInfoUtil.getPhoneModel());
        tvDeviceIemi.setText(PhoneInfoUtil.getPhoneImei());
        tvDevicePhoneNumber.setText(PhoneInfoUtil.getPhoneNum());
        tvDeviceGsp.setText(PhoneInfoUtil.isGpsEnabled() + "");
        tvDeviceFrom.setText(PhoneInfoUtil.getManufacturer());
        tvDeviceOperatorName.setText(PhoneInfoUtil.getSimOperatorName() + "");



        switch (PhoneInfoUtil.getNetworkType()) {

            case 0:
                tvDeviceNet.setText("无网络");
                break;

            case 1:
                tvDeviceNet.setText("网络断开");
                break;

            case 2:
                tvDeviceNet.setText("wifi");
                break;
            case 3:
                tvDeviceNet.setText("wifi");
                break;
            case 4:
                tvDeviceNet.setText("移动数据");
                break;

            default:

                break;


        }


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
