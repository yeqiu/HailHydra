package com.yeqiu.hydra.view.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.constant.AppConstant;
import com.yeqiu.hydra.utils.APPInfoUtil;
import com.yeqiu.hydra.utils.ActivityUtils;
import com.yeqiu.hydra.utils.SharedUtil;

import java.util.Random;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/06/26
 * @describe：启动欢迎页
 * @fix：
 */
public class SplashActivity extends BaseActivity {

    private ImageView ivSplash;

    @Override
    protected Object getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        showHeadLayout(false);

        ivSplash = (ImageView) findViewById(R.id.iv_splash);
    }

    @Override
    protected void initData() {


        int randomId = getRandomId();

        ivSplash.setImageResource(randomId);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toNextActivity();
                finish();
            }
        }, 500);


    }


    private void toNextActivity() {

        if (showLead()) {
            ActivityUtils.toLeadActivity();
        } else {

            ActivityUtils.toMainActivity();
        }

        finish();
    }


    private boolean showLead() {

        //本地记录的versionCode
        int versionCode = SharedUtil.getInstance().getInt(AppConstant.currentVersion, 0);
        //本app的versionCode
        int currentVersionCode = APPInfoUtil.getVersionCode();
        //存入本地versionCode
        return currentVersionCode > versionCode;

    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    protected void addStatusViewWithColor(int colorId) {
//        super.addStatusViewWithColor(colorId);
    }

    private int getRandomId() {

        int id = new Random().nextInt(30) + 1;
        String idName = "bg_hydra_" + id;

        int resId = getResources().getIdentifier(idName, "drawable", getPackageName());

        if (resId == 0) {
            resId = R.drawable.bg_hydra_1;
        }

        return resId;
    }


}
