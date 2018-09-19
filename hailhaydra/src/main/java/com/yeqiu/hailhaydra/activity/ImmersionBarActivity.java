package com.yeqiu.hailhaydra.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.app.Url;
import com.yeqiu.hydrautils.common.JumpUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/17
 * @describe：用法详见 HydraBaseActivity 中的 initImmersionBar()
 * @fix：
 */
public class ImmersionBarActivity extends BaseActivity {


    private TextView tvImmersionBar;
    private Button btTopPic;
    private Button btSetLight;
    private Button btSetDark;

    @Override
    protected Object getContentView() {
        return R.layout.activity_immersion;
    }

    @Override
    protected void initView() {

        setHeaderTitle("沉浸式");

        tvImmersionBar = (TextView) findViewById(R.id.tv_immersion_bar);
        btTopPic = (Button) findViewById(R.id.bt_all_pic);

        btSetLight = (Button) findViewById(R.id.bt_set_light);
        btSetDark = (Button) findViewById(R.id.bt_set_dark);

        btTopPic.setOnClickListener(this);
        btSetDark.setOnClickListener(this);
        btSetLight.setOnClickListener(this);
        tvImmersionBar.setOnClickListener(this);
        findViewById(R.id.bt_more).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected boolean isSwipeBack() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_immersion_bar:
                JumpUtils.jumpToBrowserActivity(Url.immersion);
                break;
            case R.id.bt_all_pic:
                JumpUtils.jumpToActivityByClass(AllPicActivity.class);
                break;

            case R.id.bt_set_light:
                setStatusBarDarkFont(false);
                break;
            case R.id.bt_set_dark:
                setStatusBarDarkFont(true);
                break;
            case R.id.bt_more:
                JumpUtils.jumpToBrowserActivity(Url.immersion_apk);
                break;
        }
    }
}
