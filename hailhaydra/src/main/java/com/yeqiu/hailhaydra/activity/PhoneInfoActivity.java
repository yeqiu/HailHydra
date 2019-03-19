package com.yeqiu.hailhaydra.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.utils.PhoneInfoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/13
 * @describe：
 * @fix：
 */
public class PhoneInfoActivity extends Activity {


    @BindView(R.id.tv_device_is_phone)
    TextView mTvDeviceIsPhone;
    @BindView(R.id.tv_device_sdk_version)
    TextView mTvDeviceSdkVersion;
    @BindView(R.id.tv_device_model)
    TextView mTvDeviceModel;
    @BindView(R.id.tv_device_iemi)
    TextView mTvDeviceIemi;
    @BindView(R.id.tv_device_phone_number)
    TextView mTvDevicePhoneNumber;
    @BindView(R.id.tv_device_gsp)
    TextView mTvDeviceGps;
    @BindView(R.id.ll_info_root)
    LinearLayout mLlInfoRoot;
    @BindView(R.id.tv_device_from)
    TextView mTvDeviceFrom;
    @BindView(R.id.tv_device_operator_name)
    TextView mTvDeviceOperatorName;
    @BindView(R.id.tv_device_net)
    TextView mTvDeviceNet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);
        ButterKnife.bind(this);
        initData();
    }


    /**
     * 权限
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     */
    private void initData() {

        mTvDeviceIsPhone.setText(PhoneInfoUtil.isPhone() + "");
        mTvDeviceSdkVersion.setText(PhoneInfoUtil.getSDKVersion() + "");
        mTvDeviceModel.setText(PhoneInfoUtil.getPhoneModel());
        mTvDeviceIemi.setText(PhoneInfoUtil.getPhoneImei());
        mTvDevicePhoneNumber.setText(PhoneInfoUtil.getPhoneNum());
        mTvDeviceGps.setText(PhoneInfoUtil.isGpsEnabled() + "");
        mTvDeviceFrom.setText(PhoneInfoUtil.getManufacturer());
        mTvDeviceOperatorName.setText(PhoneInfoUtil.getSimOperatorName() + "");
        mTvDeviceNet.setText(PhoneInfoUtil.getNetworkType() + "");


    }
}
