package com.yeqiu.hailhaydra.activity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.ui.HaydraToast;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/17
 * @describe：
 * @fix：
 */
public class ToastActivity extends BaseActivity implements View.OnClickListener {

    Button buttonSuccessToast;
    Button buttonInfoToast;
    Button buttonWarningToast;
    Button buttonNormalToastWoIcon;
    Button buttonNormalToastWIcon;

    @Override
    protected Object getContentView() {
        return R.layout.activity_toast;
    }

    @Override
    protected void initView() {
        setHeaderTitle("带图标的Toast");

        buttonSuccessToast = (Button) findViewById(R.id.button_success_toast);
        buttonInfoToast = (Button) findViewById(R.id.button_info_toast);
        buttonWarningToast = (Button) findViewById(R.id.button_warning_toast);
        buttonNormalToastWoIcon = (Button) findViewById(R.id.button_normal_toast_wo_icon);
        buttonNormalToastWIcon = (Button) findViewById(R.id.button_normal_toast_w_icon);


        buttonSuccessToast.setOnClickListener(this);
        buttonInfoToast.setOnClickListener(this);
        buttonWarningToast.setOnClickListener(this);
        buttonNormalToastWoIcon.setOnClickListener(this);
        buttonNormalToastWIcon.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onClick(View view) {

        ///需要自定义布局 参考HaydraToast的make();

        Drawable icon = getResources().getDrawable(R.drawable.hydra);

        switch (view.getId()) {

            case R.id.button_success_toast:
                HaydraToast.showBlue("蓝色背景", icon);
                break;
            case R.id.button_info_toast:
                HaydraToast.showGreen("绿色背景", icon);
                break;
            case R.id.button_warning_toast:
                HaydraToast.showYellow("黄色背景", icon);

                break;
            case R.id.button_normal_toast_wo_icon:
                HaydraToast.show("这是一个普通的没有ICON的Toast");
                break;
            case R.id.button_normal_toast_w_icon:
                HaydraToast.show("这是一个普通的包含ICON的Toast", icon);
                break;
            default:
                break;
        }
    }
}
